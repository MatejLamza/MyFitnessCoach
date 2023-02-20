package matej.lamza.mycoach.data.local

data class Coach(
    override val name: String,
    override val lastName: String,
    override val dateOfBirth: String,
    val measurements: Measurements
) : BasicInformation
