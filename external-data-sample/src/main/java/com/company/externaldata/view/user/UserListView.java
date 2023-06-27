package com.company.externaldata.view.user;

import com.company.externaldata.entity.User;
import com.company.externaldata.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "users", layout = MainView.class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "50em")
public class UserListView extends StandardListView<User> {
}