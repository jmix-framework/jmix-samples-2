export class RouteUtils {
    public static getBreadcrumb = (path: string) => {
        if (path.includes("/users")) {
            return {
                id: "users",
                name: "Users",
                path: "/users",
            }
        } else
            return {
                id: "home",
                name: "Home",
                path: "/home",
            };
    }
}