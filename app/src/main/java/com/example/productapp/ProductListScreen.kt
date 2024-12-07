package com.example.productapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productapp.data.Product
import com.example.productapp.ui.theme.ProductAppTheme
import androidx.compose.material3.MaterialTheme

class ProductListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductAppTheme {
                ProductListScreen()
            }
        }
    }
}

@Composable
fun ProductListScreen() {
    val productList = listOf(
        Product("Tesla Model S", 79999.0, "Electric luxury sedan with advanced technology", R.drawable.tesla_model_s),
        Product("BMW M3", 70999.0, "Sporty luxury sedan with high performance", R.drawable.bmw_m3),
        Product("Audi A6", 54999.0, "Elegant luxury sedan with a powerful engine", R.drawable.audi_a6),
        Product("Mercedes-Benz G-Class", 131000.0, "Iconic off-road vehicle with a rugged design", R.drawable.mercedes_g_class),
        Product("Ford Mustang", 42999.0, "American muscle car with an aggressive look", R.drawable.ford_mustang),
        Product("Porsche 911", 99999.0, "High-performance sports car with timeless design", R.drawable.porsche_911),
        Product("Chevrolet Camaro", 43999.0, "Sporty American car with a modern design", R.drawable.chevrolet_camaro),
        Product("Lamborghini Huracán", 249999.0, "Exotic supercar with outstanding performance", R.drawable.lamborghini_huracan),
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(productList) { product ->
            ProductListItem(product)
        }
    }
}

@Composable
fun ProductListItem(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {

            }
    ) {

        Text(text = product.name, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "${product.price} €", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Image(painter = painterResource(product.imageUrl), contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ProductAppTheme {
        ProductListScreen()
    }
}
