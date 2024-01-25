import {httpClient} from "../../hooks/httpClient";

export const HomePage = () => {
  httpClient.get("/admin").then((res) =>{
    console.log(res);
  })
  return(
      <div>Hi from home</div>

  )
}