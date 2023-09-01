import {Redirect, Route, Switch} from "react-router";
import {UserListPage} from "../pages/users/UserListPage.tsx";
import {UserPage} from "../pages/users/UserPage.tsx";
import {HomePage} from "../pages/home/HomePage.tsx";


export function JmixRoute() {
    return (
        <Switch>
            <Route path="/users/:subscriptionId">
                <UserPage/>
            </Route>
            <Route path="/users">
                <UserListPage/>
            </Route>
            <Route path="/home">
                <HomePage/>
            </Route>
            <Route path="/">
                <Redirect to="/home"/>
            </Route>
        </Switch>
    );
}