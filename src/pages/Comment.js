import { Avatar, Col, Row, Input, Space, Button } from "antd";
import { Comment as AComment } from "@ant-design/compatible";
import React, { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { GoogleMap, useJsApiLoader } from "@react-google-maps/api";
import "./Comment.css";
import axios from "axios";
import PropTypes from "prop-types";

const containerStyle = {
  width: "100%",
  height: "400px"
};

const center = {
  lat: -3.745,
  lng: -38.523
};
const CommentInput=({setEdit,onComment,data={},...props})=>{

  CommentInput.propTypes = {
    setEdit: PropTypes.func,
    onComment: PropTypes.func.isRequired,
    data: PropTypes.object,
  };

  const [text,setText]=useState("");
  return (
    <div className="comment-input">
      <Input.TextArea autoFocus placeholder="leave a message" rows={4} value={text} {...props} onChange={e=>{
        setText(e.target.value);
      }}/>
      <Space className="button">
        {setEdit&&<Button onClick={()=>setEdit(false)}>Cancel</Button>}
        <Button type="primary" onClick={()=>{
          const contractorId = JSON.parse(sessionStorage.getItem("item")).id;
          axios.post("http://localhost:8080/commentSave",{contractorId, parentCommentId: 0,childCommentId:0,...data,content:text,userId:0}).then(res=>{
            console.log(res);
            setEdit(false);
            setText("");
            onComment();
          });
        }}>Anonymous</Button>
        <Button type="primary" onClick={()=>{
          const contractorId = JSON.parse(sessionStorage.getItem("item")).id;
          axios.post("http://localhost:8080/commentSave",{contractorId, parentCommentId: 0,childCommentId:0,...data,content:text,userId:1}).then(res=>{
            console.log(res);
            setEdit(false);
            setText("");
            onComment();
          });
        }}>After Login</Button>
      </Space>
    </div>
  );
};
const CommentWrap=({onComment,data})=>{

  CommentWrap.propTypes = {
    onComment: PropTypes.func.isRequired,
    data: PropTypes.object.isRequired,
  };

  const [edit,setEdit]=useState(false);
  return <AComment
    actions={[edit?<CommentInput onComment={onComment} setEdit={setEdit} data={data}/> : <span onClick={()=>setEdit(true)}>Reply to</span>]}
    author={<a>{data.userId}</a>}
    avatar={<Avatar src="https://joeschmoe.io/api/v1/random" alt="Han Solo" />}
    content={
      <p>
        {data.content}
      </p>
    }
  >
    {data.children&&data.children.map((item, index) => <AComment key={index} data={item} onComment={onComment}/>)}
  </AComment>;
};
const Comment=()=>{
  const navigate = useNavigate();
  const [data,setData]=useState([]);
  const [item,setItem]=useState({});
  const { isLoaded } = useJsApiLoader({
    id: "google-map-script",
    googleMapsApiKey: "AIzaSyAzP_AfP38fI69-_XFm7X1TFQO1FISbnfo"
  });
  const [map, setMap] = React.useState(null);

  const onLoad = React.useCallback(function callback(map) {
    setMap(map);
  }, []);

  const onUnmount = React.useCallback(function callback(map) {
    setMap(null);
  }, []);
  const refresh=()=>{
    axios.post("http://localhost:8080/commentShow",JSON.parse(sessionStorage.getItem("item"))).then(res=>{
      if(!res.data)return;
      const comment = res.data;
      const ress = [];
      for(let i=0;i<comment.length;i++){
        if(comment[i].parentCommentId===0){
          res.push(comment[i]);
        }else{
          const parent = comment.find(i=>i.id===comment[i].parentCommentId);
          if(parent){
            parent.children = parent.children||[];
            parent.children.push(comment[i]);
          }
        }
      }
      setData(ress);
    });
  };
  useEffect(()=>{
    if(sessionStorage.getItem("item")){
      refresh();
      setItem(JSON.parse(sessionStorage.getItem("item")));
    }
  },[]);
  return <div className="comment-wrap">
    <div style={{background:"#F97432"}}>
      <div className="content fr">
        {/* <LeftOutlined onClick={()=>navigate(-1)}></LeftOutlined> */}
        <h1 style={{margin:"20px 0"}}>{item.name}</h1>
      </div>
    </div>
    <div className="comment-tabs">
      <div className="content fr">
        <span onClick={()=>document.querySelector("#Information").scrollIntoView({ behavior: "smooth" })}>Detailed Information</span>
        <span  onClick={()=>document.querySelector("#Location").scrollIntoView({ behavior: "smooth" })}>Location</span>
        <span  onClick={()=>document.querySelector("#Comment").scrollIntoView({ behavior: "smooth" })}>Comment</span>
      </div>
    </div>
    <br />
    <div className="content">
      <Row gutter={[24,24]}>
        <Col span={24}>
          <h3 id="Information">Detailed Information</h3>
          <Row gutter={[24,24]}>
            <Col span={12}>
              <h4>phone</h4>
              <p>{item.phone}</p>
            </Col>
            <Col span={12}>
              <h4>address</h4>
              <p>{item.address}</p>
            </Col>
          </Row>
          <br />

        </Col>
        <Col span={12}>
          <h3 id="Comment">Comment</h3>
          {data.map((i, index) => <CommentWrap key={index} data={i} onComment={refresh}/>)}
        </Col>
        <Col span={12}>
          <h3>Where we work</h3>
          <h3 id="Location">Location</h3>
          {isLoaded&&<GoogleMap
            mapContainerStyle={containerStyle}
            center={center}
            zoom={10}
            onLoad={onLoad}
            onUnmount={onUnmount}
          >
            <></>
          </GoogleMap>}
        </Col>
        <Col span={24}>
          <h3>leave a message</h3>
          <CommentInput onComment={refresh} rows={8}></CommentInput>
        </Col>
      </Row>

    </div>
  </div>;
};

export default Comment;