import { Routes, Route } from "react-router-dom";

import Home from "../app/Home";
import BoardList from "../board/BoardList";
import Join from "../member/Join";
import Login from "../member/Login";


function Router() {
    return(
        <Routes>
            <Route path="/" element={<Home />}></Route>

            <Route path="/boardlist" element={<BoardList/>}></Route>

            <Route path="/login" element={<Login/>}></Route>
            <Route path="/join" element={<Join />}></Route>
        </Routes>
    )
}

export default Router;
