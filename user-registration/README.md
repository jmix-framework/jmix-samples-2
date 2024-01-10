# User Registration

This example demonstrates how to create a view for self-registration of users.

In [login-view.xml](src/main/resources/com/company/userregistration/view/login/login-view.xml), the standard [forgot password](https://docs.jmix.io/jmix/flow-ui/vc/components/loginForm.html#forgotPasswordButtonVisible) button of the `loginForm` component is used to open the registration view.

The [RegistrationView](src/main/java/com/company/userregistration/view/registration/RegistrationView.java) class has the `com.vaadin.flow.server.auth.AnonymousAllowed` annotation that enables opening the view without authentication.

