package matej.lamza.mycoach.utils

import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.local.enums.Gender

object Mock {
    val clients = listOf<Client>(
        Client("", "Miljenka", "Kokot", "", Gender.FEMALE),
        Client("", "Milica", "Krmpotic", "", Gender.FEMALE),
        Client("", "Daroslav", "Kreso", "", Gender.MALE),
        Client("", "Vatroslav", "Lisinski", "", Gender.MALE),
        Client("", "Donatelo", "Gubic", "", Gender.MALE),
        Client("", "Mikelandelo", "Horvat", "", Gender.MALE),
        Client("", "Zeus", "Kranjevac", "", Gender.MALE),
        Client("", "Milorad", "Iv", "", Gender.MALE),
    )
}
