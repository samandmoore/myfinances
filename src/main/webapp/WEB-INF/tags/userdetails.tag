<%@tag description="User details template" pageEncoding="UTF-8"%>
<%@tag import="com.myfinances.users.User" %>
<%@attribute name="user" required="false" type="com.myfinances.home.viewmodels.HomeViewModel.UserDetailsViewModel"%>

<% if (user != null) { %>
    <p>${user.userName}</p>
    <a href="/users/logout">logout</a>
<% } else { %>
    impossible!
<% } %>
