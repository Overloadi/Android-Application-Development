package fi.jokufirma.youtubeandroidplayerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {

    Button playVideo;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private String link;
    EditText linkText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkText = (EditText) findViewById(R.id.textViewLink);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(link);
            }
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(MainActivity.this, "Failed to initialize player", Toast.LENGTH_SHORT).show();
            }

        };

        playVideo = (Button) findViewById(R.id.playVideoButton);
        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linkText.getText().toString().length() < 3) {
                    link = "KEkrWRHCDQU";
                }
                else {
                    link = linkText.getText().toString();
                }
                youTubePlayerView.initialize("AIzaSyBWBOMmr3UG7pLdQuwI2oJAaBB5iRDgy3U",onInitializedListener);
            }
        });

    }
}
