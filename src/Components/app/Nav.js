import {Link} from "react-router-dom";
import {AuthContext} from "../context/AuthProvider";
import {useContext} from "react";


function Nav(){

    const { auth,setAuth } = useContext(AuthContext);

    return(
        <nav className="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
            <div className="container">

                <div className="navbar-collapse collapse justify-content-between" id="navbar-content">
                    <ul className="navbar-nav mr-auto">

                        <li className="nav-item">
                            <Link className="nav-link" to="/"><i className="fas fa-home"> </i> Home/</Link>
                        </li>

                        <li className="nav-item dropdown">

                            <div className="nav-link dropdown-toggle" id="navbarDropdown"
                                 role="button" data-toggle="dropdown" aria-haspopup="true"
                                 aria-expanded="false"> 게시판</div>

                            <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                            <Link className="dropdown-item" to="/boardlist">글 목록</Link>
                                <Link className="dropdown-item" to="/bbswrite">글 추가</Link>
                            </div>

                        </li>
                    </ul>

                    <ul className="navbar-nav ml-auto">

                        {
                            (auth) ?
                                <>
                                    {/* 회원 정보 */}
                                    <li className="nav-item">
                                        <span className="nav-link"> {auth} 님 반갑습니다 <i className="fab fa-ello"></i> &nbsp;</span>
                                    </li>

                                    {/* 로그아웃 */}
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/logout"><i className="fas fa-sign-out-alt"></i>  </Link>
                                    </li>
                                </>
                                :
                                <>
                                    {/* 로그인 */}
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/login">로그인</Link>
                                    </li>

                                    {/* 회원가입 */}
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/join">회원가입</Link>
                                    </li>
                                </>
                        }

                    </ul>


                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/login">로그인</Link>
                        </li>


                    <li className="nav-item">
                        <Link className="nav-link" to="/join">회원가입</Link>
                    </li>
                    </ul>
                </div>
            </div>
        </nav>

    )
}

export default Nav;