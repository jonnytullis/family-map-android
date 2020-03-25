package async;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import _request.RegisterRequest;
import _result.RegisterResult;
import client.HttpClient;

public class RegisterAsync extends AsyncTask<RegisterRequest, Integer, RegisterResult> {
    private Fragment parentFragment;

    public RegisterAsync(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    @Override
    protected RegisterResult doInBackground(RegisterRequest... registerRequests) {
        HttpClient client = HttpClient.getInstance();
        return client.register(registerRequests[0]);
    }

    @Override
    protected void onPostExecute(RegisterResult result) {
        if (result.isSuccess()) {
            // Call async to get all related family data
            new FetchAllPersonsAsync(parentFragment).execute();
        } else {
            Toast toast = Toast.makeText(parentFragment.getContext(), result.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
