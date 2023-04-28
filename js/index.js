import { query } from "./Api.js";
import ContractorList from "./ContractorList.js";
import Selector from "./Selector.js";

window.onload = function() {
  const dom = document.querySelector("#react_mount");
  const root = ReactDOM.createRoot(dom);
  const jobs = [
    "Install front door",
    "Connect boiler",
    "Remove CO2",
    "Stop petrol fire",
    "Shut fridge door"
  ];
  const contractors = [
    {
      name: "Sally Plumber",
      phone: "01234 567890",
      certifications: ["Heat Geek", "Thermal Nerd", "Temperature Person"]
    },
    {
      name: "Tim from down the road",
      phone: "09876 543210",
      certifications: [],
    }
  ];
  root.render(
    <div>
      <Selector entries={jobs} />
      <br />
      <br />
      <ContractorList contractors={contractors} />
    </div>
  );
};
