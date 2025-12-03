package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.data.MenuItemEntity

@Composable
fun MenuItemCard(item: MenuItemEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.description, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$${item.price}", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = item.imageRes ?: R.drawable.greek_salad),
                contentDescription = item.title,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemCardPreview() {
    val fakeItem = MenuItemEntity(
        id = 1,
        title = "Greek Salad",
        description = "Fresh veggies with feta cheese",
        price = 12.0,
        imageRes = R.drawable.greek_salad, // use your local drawable
        category = "Salads"
    )

    MenuItemCard(item = fakeItem)
}
