<%@tag description="User details template" pageEncoding="UTF-8"%>
<%@tag import="com.myfinances.users.User" %>
<%@attribute name="user" required="false" type="com.myfinances.home.viewmodels.HomeViewModel.UserDetailsViewModel"%>

<p class="user-details">
<% if (user != null) { %>
    welcome, ${user.userName}.
    <a href="/sessions/logout">logout</a>
<% } else { %>
    impossible!
<% } %>
</p>
