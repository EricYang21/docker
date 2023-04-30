import { Input, Select,message } from "antd";
import { useNavigate } from "react-router-dom";
import option from "./option";
import { useState } from "react";
import axios from "axios";
import bg from "../assets/bg5.png";
import Card from "../Components/Card";

function Home() {
  const navigate = useNavigate();
  const [postcode ,setPostcode ]=useState();
  const [source ,setSource ]=useState();
  const [task ,setTask ]=useState();
  return (
    <>
      <div className="index_img content">
        <img width={"100%"} src={bg} alt="" />
      </div>
      <div className="home-banner">
        <h1>Energy Project</h1>
        <h4>For Bristol Energy Network</h4>
        <div className="content fr">
          <div className="blank">
            <div>&nbsp;</div>
            <Select allowClear className='input' placeholder='source' options={["GreenRegister",
              "TrustMark",
              "MCS",
              "VerifiedTrade"].map(item=>({label:item,value:item}))} value={source} onChange={e=>setSource(e)}/>
          </div>
          <div className="blank">
            <div>Where are you?</div>
            <Input allowClear className='input' placeholder='Tower or postcode' value={postcode} onChange={e=>setPostcode(e.target.value)}/>
          </div>
          <div className="blank">
            <div>What do you need?</div>
            <Select allowClear className='input' placeholder='eg. "Retrofit" and "Electrician"' options={Object.values(option).map(v=>({label:v,value:v}))} value={task} onChange={e=>setTask(e)}/>
          </div>
          <div >
            <div>&nbsp;</div>
            <div className="find-btn center" onClick={()=>{
              if(!/[A-Z]{1,2}[0-9][0-9A-Z]?\s?[0-9][A-Z]{2}$/.test(postcode)){
                message.error("Please enter a valid postcode");
                return;
              }
              if(!task){
                message.error("Please enter task");
                return;
              }
              if(!source){
                message.error("Please enter source");
                return;
              }
              axios.post("http://docker_backend_1:8080/query",{postcode,task,source}).then(res=>{
                if(res&&res.data&&res.data.length>0){
                  sessionStorage.setItem("data",JSON.stringify(res.data));
                  navigate("/list");
                }else{
                  sessionStorage.setItem("data","[]");
                  navigate("/list");
                }
              });

            }}>
              Find tradespeople
            </div>
          </div>
          
        </div>
      </div>

      <div className="main content">
        <h2 className="center">
        Who We Support:
        </h2>
        <div className="fr">
          <div className="blank support-item">
            <img height={120} src="https://i.postimg.cc/V6RWP2zn/working.png" alt="" />
            <h3>BEN</h3>
            <div>Bristol Energy Network Officers</div>
          </div>
          <div className="blank support-item">
            <img height={120} src="https://i.postimg.cc/1Rbrh0QW/customer.png" alt="" />
            <h3>Potential Retrofit Clients</h3>
            <div>Potential retrofit clients will need to use the website to find the appropriate retrofit provider</div>
          </div>
          <div className="blank support-item">
            <img height={120} src="https://i.postimg.cc/yNz7wbQV/provider.png" alt="" />
            <h3>Retrofit Provider</h3>
            <div>Our website to aggregate websites that contain their information such that they will be discovered more easily.</div>
          </div>
        </div>
 
        <Card />
      </div>

    </>
  );
}

export default Home;
