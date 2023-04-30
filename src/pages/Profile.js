import React, { useEffect, useState } from "react";
import "./Profile.css";
import { Avatar, Row,Col, Input, Button, Affix, Radio, DatePicker, Space, Modal, Upload, message } from "antd";
import axios from "axios";
import PropTypes from "prop-types";

const Edit=({value,request,type,data,refresh})=>{
  Edit.propTypes = {
    value: PropTypes.string,
    request: PropTypes.func,
    type: PropTypes.string,
    data: PropTypes.object,
    refresh: PropTypes.func
  };
  const [current,setCurrent]=React.useState(value);
  const [edit,setEdit]=React.useState(false);
  useEffect(()=>{
    setCurrent(value);
  },[value]);
  let content = <Input style={{maxWidth:200}} value={current} onChange={(e)=>setCurrent(e.target.value)} />;
  if(type==="radio"){
    content = <Radio.Group value={current} onChange={(e)=>setCurrent(e.target.value)}>
      <Radio value={"male"}>male</Radio>
      <Radio value={"female"}>female</Radio>
    </Radio.Group>;
  }else if(type==="date"){
    content = <DatePicker allowClear={false} format='YYYY/MM/DD'  onChange={date=>{
      setCurrent(date.format("YYYY/MM/DD"));
    }}/>;
  }else if(type==="password"){
    content = <Input.Password style={{maxWidth:200}} value={current} onChange={(e)=>setCurrent(e.target.value)} />;
  }else if(type==="comment"){
    content = <Input.TextArea  value={current} onChange={(e)=>setCurrent(e.target.value)} />;
  }
  return <div className="fr editWrap">
    {edit?content:<span>{type==="password"?"******":current}</span>}
    {!edit?<Space><Button className='editBtn' type='link' onClick={()=>setEdit(true)}>edit</Button>
      {type==="comment"&&<Button className='editBtn' type='link' danger onClick={()=>{
        axios.post("/commentDelete",{...data,commentId:data.id}).then(res=>{
          refresh&&refresh();
        });
      }}>delete</Button>}
    </Space>:null}
    {edit?<Button type='link' onClick={()=>{
      request&&request(current);
      setEdit(false);
    }}>save</Button>:null}
    {edit?<Button type='link' onClick={()=>setEdit(false)}>cancel</Button>:null}
  </div>;
};
const infoSpan=12;
const test={
  "user":{
    "id":5,
    "email":"a",
    "username":"a",
    "avatar":"https://avatarspe.oss-cn-beijing.aliyuncs.com/2023/04/24/nn3ix31rfrlmi7xfqqfn7uw1rjbdz10dd3tq3ap1ux8ykqqc7cb350ed0e9dcd9ba5908b15eac29443.jpg",
    "password":"a",
    "sex":"a",
    "birth":"00/11/15"
  },
  "browseHistory":[
    {
      "userId":5,
      "link":"link"
    }
  ],
  "comments":[
    {
      "id":2,
      "content":"that's great",
      "contractorId":60,
      "userId":5,
      "parentCommentId":0,
      "childCommentId":0
    },
    {
      "id":3,
      "content":"bad",
      "contractorId":60,
      "userId":5,
      "parentCommentId":0,
      "childCommentId":0
    },
    {
      "id":4,
      "content":"you are the best",
      "contractorId":3,
      "userId":5,
      "parentCommentId":0,
      "childCommentId":0
    },
    {
      "id":12,
      "content":"you are not best",
      "contractorId":113,
      "userId":5,
      "parentCommentId":0,
      "childCommentId":0
    },
    {
      "id":13,
      "content":"you are not best",
      "contractorId":113,
      "userId":5,
      "parentCommentId":0,
      "childCommentId":0
    }
  ]
};
// eslint-disable-next-line react/display-name
export default () => {
  const [data,setData]=useState(test);
  const [uploadShow,setUploadShow]=useState(false);
  const [imageUrl, setImageUrl] = useState();
  const userId=sessionStorage.getItem("userId");
  const refresh = ()=>{
    if(!userId)return;
    axios.post("/showUserCenter",{userId}).then(res=>{
      setData(res.data);
    });
  };
  useEffect(()=>{
    refresh();
  },[]);
  const handleRes=res=>{
    if(!res.data.isOk){
      message.error("please try again.");
      return;
    }
    refresh();
  };
  return <div className="content">
    <br />
    <div className="profile">
      <Affix offsetTop={22}>
        <div className="left">
          <div className="name" onClick={()=>document.querySelector("#info").scrollIntoView({ behavior: "smooth" })}>
                        personal information
          </div>
          <div className="name" onClick={()=>document.querySelector("#browse").scrollIntoView({ behavior: "smooth" })}>
                        browse history
          </div>
          <div className="name" onClick={()=>document.querySelector("#comment").scrollIntoView({ behavior: "smooth" })}>
                        comment management
          </div>

          <div className="satisfy">
            <div className="fr">
              {/* eslint-disable-next-line max-len */}
              <img src="data:image/png;base64,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" alt="" />
              <span>Very dissatisfied</span>
            </div>
            <div className="fr">
              {/* eslint-disable-next-line max-len */}
              <img src="data:image/png;base64,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" alt="" />
              <span>dissatisfied</span>
            </div>
            <div className="fr">
              {/* eslint-disable-next-line max-len */}
              <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEQAAABECAYAAAA4E5OyAAAFN0lEQVR4Xu2cT2wUdRTHP69/RMUCIhVRu4uaFKPopfUihMQYD4bdDQYJSdXY4p9wMJoA6k3Qm0pJNB6IRlqjJSFIxN0lHowxIcWL9KLUSBOluwYRi2hBVKTsMzuttrM7uzu7M7Mza+Z32sP83vv+vvveb957v/cbweOhpx5s52LLGoQ7QTpBVwDXIbShtBnqhfMo54FfQI6DjqGMMm/qsCz7ZMJjiCbx4oUyzaztQqQH5QFgJUiNelSBYwiforpXoodGvMA7V2aNQIth6beJNq7madA+NG8NHgxhFGSAP3hbbk/mLcr14ZgQ/XXdIs5NPQc8C7LYdYSWAvUs8CYLWt6Qaw/+5qbOmglRRfgh/jg5fQ2RdjdB2ZalOkGTvEBH6j0R8u7leNREiGbX3QaXB1FWO0bghgBhGJp7JXLwO6fiqiZEM4kNkHsHZKFT5e7O10mam5+Umz/+0Ilc24RMu0jsdZAt5H8HcRhuo7voSD9fqwvZWphqVyvZmwZAHwkiD8WYZIiJk33SPXKpWrwVCZkm48aPgLXVCvf5+UNEfnxIpDpSyhJiuEk28X7jWEbhXyBDRJKPVeM+5QnJxnaistXnf9qZetF+iaS32RVSkhDjbSK6L7AbqP0VKiobJZrcb2eKJSFGnKFTI8F7tdpZktUzOom0dNmJU4oImYlADwcm6KqVg6LthGE6Umsq7SfFhGRjfajscQtHoOSIbpJIeqAcJhMhRqI2OTXmW27iNXv53GdhS2e5hNBMSCa2HWSH17j8la87JJp+uRSG/wgx6hlX5cbrl8L7RYue5c+m5aXqKbOEZBNbUd3pF8y66hXZJpFkv5XOWUIy8VHgjroC80uZMCqR1MqShBg1UJqO+oXPH725bqsarWEhmo31o7LFH2A+aRXdJZF0UVoyTUgm9hXIXT5B80mtfi3R9N1F8ZtxbvJ38+najwrMIqcuKQf2T/D5Z9O13/vuX8T6De20tFasNJgEuSWnNNuqXHF5aeG5j+h4fD2Co7LbXKX79v5MOpkvis+OWGIxG3uur8oS3JJTVqnysCxPHZj7jGgm/hJQMlCpahXA5ifGuHAhZ5o2f34Tu9/trEqUW3IqKN0u0dQrZkLG40MIPVWhLfOwWwtxS075dcmQRJOPFlrIl0C3W4S4ZepuyamwrqMSTd1TSMgJYLlbhLi1Gbolp8K6xiWausVMSDY+gbLELUIaSo5wRiIp06ljflP9C5jXUAtxD+xFiaauLHSZkJA5jIiGLlPkMt8Dpo3FPYsMvKQTEk3d6ulrN/AUmAFavXYTHzTuyZxT+q0DM1dDd6cQ6zzfMnR3Nbmr84KcqbNM7lxO/50hrOfsEul/HkJYIJr9I8ISYoFRzpQQvS8yqypfHDnH6Z/KN/UsvaGVe1ctQGrt9bXtdWWKzIbbZOPHPGu4BY4MT7L7rVO24G5+ZhmrVnvY01fpGGKaEG8PqoJFiJ2DKo+PMoPjMjaPMmfeNuFh91ynDtsh8jdVCoZm470oZZtKbO2MQXxI6JNIarActLClyioOKbaSsOmuyIrCtkwLx9KwcdfMStjabWUlYfN/MSsNeT0kcrKv2psQ+ZXbbtoILxCViF4CfcWMpqfsNvmXCs5sW4gpxA8vIVrtK8YdvF5yvOpbK7hxTZUX6UgPVmrqt5tJ1GQhRQlheJHZwmL+vequusnDBuBvENkT6KvuVqYYfgyhjIOaPpehrEDId98tQbim4HMZvwNnUMYQjv+vPpdhdwML4nOON9UgLsoJppAQOwUiJww3+tx/ABdFcWPd2Z/TAAAAAElFTkSuQmCC" alt="" />
              <span>generally</span>
            </div>
            <div className="fr">
              {/* eslint-disable-next-line max-len */}
              <img src="data:image/png;base64,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" alt="" />
              <span>satisfy</span>
            </div>
            <div className="fr">
              {/* eslint-disable-next-line max-len */}
              <img src="data:image/png;base64,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" alt="" />
              <span>Very satisfied</span>
            </div>
          </div>
        </div>
      </Affix>
      <div className="right blank">
        <div className="fr p20">
          <div className="avatar">
            <Avatar size={80} src={data.user.avatar}/>
            {/* eslint-disable-next-line max-len */}
            <Avatar onClick={()=>setUploadShow(true)} className='btn' size={80} src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAFC0lEQVR4Xu2ba8ilUxTHf39J5H6NQiQSii80bjENNeEDmtw1Jb64jhqF1AwJZcow+EJqmnJLzIeZmkJoxMQXComkjCL3cckk+WvpOdM++33OOc9znufMeY9zVp0v77P22mv991prr7X3fsWUk6bcfmYAzDxgyhGYhcCUO8DOTYK2d6kCuKR/qvC1wTPSELB9CHAVcA5wGLBrRaX/Br4B3gKelfRdxXG12UYGgO3TgQeAvWtr1T3gN+BuSe82lFM6fCQA2D4eeBrYvSWltwPXS/q0JXk7xLQOgO3dwm2Bo1pW9kvgakl/tSl3FAAsA67JlHwKeB4Id65CETZXADdkzOskPVpFQFWeVgGwfXLh+qncjZJWVFUo5bN9H3BB8jcXofDhMPLKxrQGgO2I9+eAI5KJvgcuk1R15bt0tB2e8CJwcPJhK3ClpMgLjakyAIWBC4ATC4X2zGaPLS++pXSrpHeaaGn7TCB3+4+BfGv8AwjA49uWqgANBMD2PsB1wJKaWX29pPubGN8Za/se4OIassI7XgKekfRrv3F9AShi+mHggBqTB+u3wOWSYlUak+3wtheAQ2sK+wm4Q1LPnNETANvh7qtrVG8d3QL9myV9UFPZvuy2TwEer+mFITOqymWStlROgrYjka0D9soG/Qi8CnwBlK1uTPbRqErXorQ+qceihJccA5wPHJjp/TtwraRIoF1U6gG2HwHOzng3AKskhbB5S7Zj0ZYDF2VKbpZ0+0AAbEcmX5sxRjJ5ct5aXaKY7RuL5J1+XSopdokdNMcDbN8W7pLwfF64T7j3xJDt6DwjjI9NlJ5TSZYBEHX8ccmguyRF3E8c2Y588GCi+GeSoj3v6wGvA/smPIsl/dCG9cWqnAvE74SkwosC5hPgzfhJasXbbB8EbEp03yZpUU8AbIdHvJ8Zu6ANhWwvBCK8Dh8A5tdR+Ul6oynoBeD59neqpOgp/qOuECiOrN7LJj6tyRFVIfOWLK9UsS3id00Lc/e1Z2cAkCfVKsZ3eBq1v1UWdKQAFG4fpXROUVOsB+KQI+jootbP9+74FqXsUOEwVgCK+IuGJI35n4EVvTpE22cA9wL7J4hFTlgyTB4aNwDnAQ9lSz+wPS5AeCwbd6ek1+rETvCOG4BohRcnSm+QtLKKEbaDLw2HTZKiJa5F4wbgZeDIROM41a3UIRadX5wqd+grSZfWsn4eeMBmYI9E6UWStlUxwnYUYlGQdehPSXlzNlDUuD1g6gGY+hCY+iQ49dtg9OPTWwgVhUh0gNNZCnf2qJITpoHbV8Iw2c1QUo5ObzuceMI4DkTiSc54zwNSfx/Dkdj8AqBO8LfBO9ZSuA0DmsqoDUCRtN7O7t8WDnu/39SApuOL9wXpadJ2SWelcsvuBV7JHjlUbmObKtz2+JK2equkSwYBkNfwQz9xaduguvJsx/Hahcm4OQcrZR5QVsPH9XKExsSQ7XD1uN5Pac7RWhkAZTX8L8DKSQGhMD6O1fZLrC89XO11PR5XV6tKlnsjEDmi1/uAcXpI531AxHjq9h2dlkuKq7cu6vdCJMrXpeO0qMW510paUyavHwBRRd30PwAh3jo80euKrcorsQiHeP056FKzxQVrRVTE/Ooyt++7DZZNndXwnXeCbT2EbsVaIB5ndd4JVr5mH+gBvbSr+s8PbVk3SM6wt8hDAzBIoUn5PgNgUlZqVHrOPGBUyE6K3JkHTMpKjUrPfwGerYtf3LH0qAAAAABJRU5ErkJggg=="/>
          </div>
                    &nbsp;&nbsp;
          <span style={{fontSize:22}}>{data.user.username}</span>
        </div>
        <div id='info' className="name">
                    personal information
        </div>
        <div className="p20">
          <Row gutter={[22,22]}>
            <Col span={infoSpan}>
              <div className="row fr">
                <label>email:</label>
                {/* <Edit value={data.user.email} request={v=>{
                  axios.post('/changeemail',{email:v,userId}).then(handleRes)
                }}/> */}
                <span>{data.user.email}</span>
              </div>
            </Col>
            <Col span={infoSpan}>
              <div className="row fr">
                <label>username:</label>
                <Edit value={data.user.username} request={v=>{
                  axios.post("/changeusername",{username:v,userId}).then(handleRes);
                }}/>
              </div>
            </Col>
            <Col span={infoSpan}>
              <div className="row fr">
                <label>userId:</label>
                <span>{userId}</span>
              </div>
            </Col>
            <Col span={infoSpan}>
              <div className="row fr">
                <label>sex:</label>
                <Edit value={data.user.sex} type='radio' request={v=>{
                  axios.post("/changesex",{sex:v,userId}).then(handleRes);
                }}/>
              </div>
            </Col>
            <Col span={infoSpan}>
              <div className="row fr">
                <label>password:</label>
                <Edit value={data.user.password} type='password' request={v=>{
                  axios.post("/changepassword",{password:v,userId}).then(handleRes);
                }}/>
              </div>
            </Col>
            <Col span={infoSpan}>
              <div className="row fr">
                <label>date of birth:</label>
                <Edit value={data.user.birth} type='date' request={v=>{
                  axios.post("/changebirth",{birth:v,userId}).then(handleRes);
                }}/>
              </div>
            </Col>
          </Row>
        </div>
        <div id='browse' className="name">
                    browse history
        </div>
        <div className="p20">
          {data.browseHistory.map((item,index)=><p key={index}>{"http://localhost:3000"+item.link}</p>)}
        </div>
        <div id='comment' className="name">
                    comment management
        </div>
        <div className="p20">
          {data.comments.map((item,index)=><div key={index} className="fr row"><Edit key={index} value={item.content} type='comment' refresh={refresh} data={item} request={v=>{
            axios.post("/commentUpdate",{...item,commentId:item.id,content:v}).then(handleRes);
          }}/></div> )}
        </div>
      </div>
    </div>
    <Modal closable={false} open={uploadShow} title={null} onCancel={()=>setUploadShow(false)} onOk={()=>{
      if(!imageUrl) return message.error("please select a picture");
      axios.post("/changeavatar",{avatar:imageUrl,userId}).then(handleRes);
      setUploadShow(false);
    }}>
      <div className='center'>
        <div style={{width:100}}>
          <Upload accept='image/*'  listType="picture-card"
            onChange={info => {
              if (info.file.status === "done") {
                console.log(info);
                setImageUrl(info.file.response);
              }
            }}
            showUploadList={false} action={"http://docker_backend_1:8080/file/upload"}>
            {imageUrl ? <img src={imageUrl} alt="avatar" style={{ width: "100%" }} /> : <div style={{ marginTop: 8 }}>Upload</div>}
          </Upload>
        </div>
      </div>
    </Modal>
  </div>;
};