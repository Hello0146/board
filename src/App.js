import {BrowserRouter} from "react-router-dom";
import Nav from "./Components/app/Nav";
import Main from "./Components/app/Main";
import Home from "./Components/app/Home";
import AuthProvider from "./Components/context/AuthProvider";
import HttpHeadersProvider from "./Components/context/HttpHeadersProvider";

function App() {

  return(
      <div>
        <BrowserRouter>

            <AuthProvider>
                <HttpHeadersProvider>

                    <Nav />
                    <Main />

                </HttpHeadersProvider>
            </AuthProvider>

        </BrowserRouter>
      </div>
  );
}

export default App;