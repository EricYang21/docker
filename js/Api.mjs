const url = process.env.API_URL || "http://docker_backend_1:8080";

export function query(sources, tasks, postcode) {
  let body = {
    sources: sources,
    tasks: tasks,
    postcode: postcode,
  };
  return fetch(url + "/query", {
    method: "POST",
    body: JSON.stringify(body),
    headers: {
      "Content-Type": "application/json"
    }
  })
    .then(data => data.json())
    .catch(err => console.log(err));
}
