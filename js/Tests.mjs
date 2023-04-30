// import { query } from "./Api.mjs";
// import tasks from "./Tasks.mjs";
// import { strict as assert } from "node:assert";
//
// // we need to send some task, so just send the first one we know about
// query(["TEST"], [Object.keys(tasks)[0]])
//   .then(contractors => assert.deepEqual(contractors, [
//     {
//       id: 3,
//       name: "Sally Plumber",
//       phone: "01234 567890",
//       certs: "Heat Geek",
//       address: "Too far away"
//     },
//     {
//       id: 4,
//       name: "Tim from down the road",
//       phone: "09876 543210",
//       certs: "",
//       address: "Down the road"
//     }
//   ]));
// console.log("JS tests passed successfully");