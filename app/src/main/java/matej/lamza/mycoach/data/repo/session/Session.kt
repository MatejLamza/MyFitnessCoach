package matej.lamza.mycoach.data.repo.session

interface Session {
    val isLoggedIn: Boolean
    var token: String
    var refreshToken: String
    var userId: String
}
