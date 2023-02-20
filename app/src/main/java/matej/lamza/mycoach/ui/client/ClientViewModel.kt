package matej.lamza.mycoach.ui.client

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import matej.lamza.mycoach.BuildConfig
import matej.lamza.mycoach.data.local.Client

class ClientViewModel(private val database: FirebaseDatabase) : ViewModel() {

    private val database2 = FirebaseDatabase.getInstance(BuildConfig.FIREBASE)

    fun addUserToFirestore(client: Client) {
        database2.reference.child("users").child("1").setValue(client)
            .addOnSuccessListener {
                // Write was successful!
                // ...
                Log.d("bbb", "addUserToFirestore: ")
            }
            .addOnFailureListener {
                // Write failed
                // ...
                Log.e("bbb", "addUserToFirestore: ", it)
            }
    }


}
