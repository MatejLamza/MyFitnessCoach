package matej.lamza.mycoach.common


sealed class CardStackOrientation {
    data class Vertical(
        val alignment: VerticalAlignment = VerticalAlignment.TopToBottom,
        val animationStyle: VerticalAnimationStyle = VerticalAnimationStyle.ToRight
    ) : CardStackOrientation()

    data class Horizontal(
        val alignment: HorizontalAlignment = HorizontalAlignment.StartToEnd,
        val animationStyle: HorizontalAnimationStyle = HorizontalAnimationStyle.FromTop
    ) : CardStackOrientation()
}

enum class VerticalAlignment {
    TopToBottom,
    BottomToTop,
}

enum class HorizontalAlignment {
    StartToEnd,
    EndToStart,
}

enum class VerticalAnimationStyle {
    ToRight,
    ToLeft,
}

enum class HorizontalAnimationStyle {
    FromTop,
    FromBottom
}
