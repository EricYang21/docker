import { Avatar, Col, Row, Input, Space, Button, Card } from "antd";
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
          axios.post("/commentSave",{contractorId, ...data,parentCommentId: data.id||0,childCommentId:0,content:text,userId:0}).then(res=>{
            console.log(res);
            setEdit&&setEdit(false);
            setText("");
            onComment();
          });
        }}>Anonymous</Button>
        <Button type="primary" onClick={()=>{
          const contractorId = JSON.parse(sessionStorage.getItem("item")).id;
          axios.post("/commentSave",{contractorId, ...data,parentCommentId: data.id||0,childCommentId:0,content:text,userId:sessionStorage.getItem('userId')}).then(res=>{
            console.log(res);
            setEdit&&setEdit(false);
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
  console.log(data.children);
  return <AComment
    actions={[edit?<CommentInput onComment={onComment} setEdit={setEdit} data={data}/> : <span onClick={()=>setEdit(true)}>Reply to</span>]}
    author={<a>{data.username}</a>}
    avatar={<Avatar src={data.avatar} alt="Han Solo" />}
    content={
      <p>
        {data.content}
      </p>
    }
  >
    {data.children&&data.children.map((item, index) => <CommentWrap key={index} data={item} onComment={onComment}/>)}
  </AComment>;
};
const test={"commentAndUserList":[
  {"comment":{"id":16,"content":"Apollo Electrical Solutions Ltd","contractorId":86,"userId":0,"parentCommentId":0,"childCommentId":0},"userInComment":{"avatar":"https://avatarspe.oss-cn-beijing.aliyuncs.com/2023/04/25/pq05dsbckkudxszk1v74rstvosrfi1mm9bob5qh81dkk7lzxdownload.png","username":"Anonymous"}},{"comment":{"id":17,"content":"you are the best","contractorId":86,"userId":0,"parentCommentId":16,"childCommentId":0},"userInComment":{"avatar":"https://avatarspe.oss-cn-beijing.aliyuncs.com/2023/04/25/pq05dsbckkudxszk1v74rstvosrfi1mm9bob5qh81dkk7lzxdownload.png","username":"Anonymous"}}],
  "graph":{"longitude":"-2.596472","latitude":"51.4559"}}

const fmtComment=input=>{
  const comment=input.map(i=>({...i.comment,...i.userInComment}))
  const res = [];
  for(let i=0;i<comment.length;i++){
    if(comment[i].parentCommentId===0){
      res.push(comment[i]);
    }else{
      const parent = comment.find(ii=>ii.id===comment[i].parentCommentId);
      if(parent){
        parent.children = parent.children||[];
        parent.children.push(comment[i]);
      }
    }
  }
  return res;
}
// const center={
//   lat: -2.745,
//   lng: 51.4559
// }
const Comment=()=>{
  const navigate = useNavigate();
  const [center,setCenter]=useState()
  
  const [data,setData]=useState(fmtComment(test.commentAndUserList));
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
    axios.post("/commentShow",JSON.parse(sessionStorage.getItem("item"))).then(res=>{
      if(!res||!res.data)return;
      setData(fmtComment(res.data.commentAndUserList));
      setCenter({
        lat: parseFloat(res.data.graph.latitude),
        lng: parseFloat(res.data.graph.longitude)
      })
    });
  };
  useEffect(()=>{
    if(sessionStorage.getItem("item")){
      refresh();
      setItem(JSON.parse(sessionStorage.getItem("item")));
    }
  },[]);
  return <div className="comment-wrap">
    <div style={{}}>
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
          {isLoaded&&!!center&&<GoogleMap
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