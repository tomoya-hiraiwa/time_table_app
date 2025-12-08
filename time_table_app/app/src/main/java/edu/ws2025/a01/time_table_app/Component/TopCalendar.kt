package edu.ws2025.a01.time_table_app.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ws2025.a01.time_table_app.data.CalendarDay
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme


@Composable
fun TopCalendar(
    modifier: Modifier = Modifier,
    calendarDayList: List<CalendarDay>,
    onClick: (CalendarDay) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                Color.LightGray
            )
    ) {
        items(calendarDayList) {
            TopCalendarItem(data = it) {
                onClick(it)
            }
        }
    }
}


@Composable
fun TopCalendarItem(
    modifier: Modifier = Modifier,
    data: CalendarDay,
    onClick: (CalendarDay) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp)
            .clickable(onClick = { onClick(data) }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            data.dayOfWeek,
            style = MaterialTheme.typography.titleLarge,
            color = if (data.dayOfWeek == "Sun") Color.Red else Color.Black
        )
        Text(
            data.date,
            style = MaterialTheme.typography.titleMedium,
            color = if (data.dayOfWeek == "Sun") Color.Red else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TopCalendarPreview() {
    Time_table_appTheme {
        TopCalendar(calendarDayList = emptyList()) {}
    }
}

@Preview(heightDp = 80, showBackground = true)
@Composable
private fun TopCalendarItemPreview() {
    Time_table_appTheme {
        TopCalendarItem(data = CalendarDay("Sun", "1")) {}
    }
}