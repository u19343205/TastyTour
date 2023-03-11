import android.widget.Toast
import androidx.test.core.app.ApplicationProvider
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityMainBinding
import com.example.tastytour.databinding.FragmentSavedBinding
import com.example.tastytour.models.ModelRestaurant
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FavouriteUnitTest {
    private lateinit var model: ModelRestaurant
    private lateinit var binding: FragmentSavedBinding

    // Set up the test by creating a new instance of the model and binding
    @Before
    fun setUp() {
        model = ModelRestaurant("Restaurant", "Location", false)
        binding = mockk(relaxed = true)
    }

    // Define a test for clicking the favorite button and adding a restaurant to favorites
    @Test
    fun `click favorite button adds restaurant to favorites`() {
        // Set the model's isFavorite property to false
        model.isFavorite = false
        // Use mockk to mock the context and return the application context
        every { binding.root.context } returns ApplicationProvider.getApplicationContext()

        // Call the favoriteButtonClickListener method on the model
        model.favoriteButtonClickListener(binding)

        // Assert that the model's isFavorite property is true
        assertTrue(model.isFavorite)
        // Verify that the savedTv background resource is set to the favorite icon and that a
        // Toast message is displayed indicating that the restaurant was added to favorites
        verify(exactly = 1) {
            binding.savedTv.setBackgroundResource(R.drawable.baseline_favorite_24)
            Toast.makeText(binding.root.context, "Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }
    // Define a test for clicking the favorite button and removing a restaurant from favorites
    @Test
    fun `click favorite button removes restaurant from favorites`() {
        // Set the model's isFavorite property to true
        model.isFavorite = true
        // Use mockk to mock the context and return the application context
        every { binding.root.context } returns ApplicationProvider.getApplicationContext()

        // Call the favoriteButtonClickListener method on the model
        model.favoriteButtonClickListener(binding)

        // Assert that the model's isFavorite property is false
        assertFalse(model.isFavorite)
        // Verify that the savedTv background resource is set to the unfavorite icon and that a
        // Toast message is displayed indicating that the restaurant was removed from favorites
        verify(exactly = 1) {
            binding.savedTv.setBackgroundResource(R.drawable.baseline_favorite_border_24)
            Toast.makeText(binding.root.context, "Removed from favorites", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
