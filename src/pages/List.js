import { Button, Card, Col, Row } from "antd";
import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./List.css";
const test=[
  {
    id:1,
    name:'name',
    address:'address',
    phone:'phone',
  }
]
const List=()=>{
  const navigate = useNavigate();
  const [data,setData]=useState(test);
  useEffect(()=>{
    if(!sessionStorage.getItem("data"))return;
    const d = JSON.parse(sessionStorage.getItem("data"));
    const set={};
    const newData = [];
    d.forEach(item=>{
      if(set[item.name])return;
      set[item.name]=item;
      newData.push(item);
    });

    setData(newData);
  },[]);
  return <div className="content main-content">
    <br />
    <Button type="primary" onClick={()=>navigate(-1)}>back</Button>
    <br />
    <br />
    <div className="content">
      <Row gutter={[24,24]} >
        {data&&data.length>0?data.map((item, index) =>(<Col key={index} span={12}>
          <Card  hoverable title={item.name} >
            <Card.Meta title={item.certs}  description={item.address} />
            <Card.Meta description={item.phone} />
            <br />
            <Button type="primary" onClick={()=>{
              sessionStorage.setItem("item",JSON.stringify(item));
              navigate(`/comment/${item.id}`);
            }}>show comment</Button>
          </Card>
        </Col>)):<p>
          xxxxxxxxxxxxxxx
        </p>}
      </Row>
    </div>

  </div>;
};

export default List;