import { Row, Col } from "antd";

const Card = () => {

  const cardsData = [
    {
      name: "TrustMark",
      imgUrl: "https://i.postimg.cc/PJwzXr36/trustmark2.png",
      desc: "TrustMark is the Government Endorsed Quality Scheme that exists to create enhanced confidence and choice for consumers engaging businesses to work in or around the home.",
      webUrl: "https://www.trustmark.org.uk/"
    },
    {
      name: "MCS",
      imgUrl: "https://i.postimg.cc/FsS7qGhj/mcs3.png",
      desc: "MCS is an industry-led quality assurance scheme, which demonstrates the quality and reliability of approved products and installation companies.",
      webUrl: "https://mcscertified.com/"
    },
    {
      name: "The Green Register",
      imgUrl: "https://i.postimg.cc/3JGn79zK/the-Green-Register2.png",
      desc: "The online register is here to help clients find construction professionals skilled in sustainable building practices.",
      webUrl: "https://www.greenregister.org.uk/"
    },
    {
      name: "Verified Trades",
      imgUrl: "https://i.postimg.cc/xj595y61/verified-Trades2.png",
      desc: "Verified by Expert Trades are trades professionals committed to delivering exceptional service.",
      webUrl: "https://verifiedtrades.co.uk/"
    }
  ];

  return <div className="cards">
    <Row gutter={[30,30]}>
      {cardsData.map((card, index)=>(
        <Col key={index} span={12}>
          <div className="card">
            <div className='card-title'>{card.name}</div>
            <img src={card.imgUrl} alt="" width={"100%"} />
            <div className="card-body">
              <p>
                {card.desc}
              </p>
              <a href={card.webUrl}><button className="card-btn">Learn More...</button></a>
            </div>
          </div>
        </Col>
      ))}
    </Row>
  </div>;
};

export default Card;