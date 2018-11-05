package es.iessaladillo.pedrojoya.pr04.ui.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import es.iessaladillo.pedrojoya.pr04.R;
import es.iessaladillo.pedrojoya.pr04.data.local.Database;
import es.iessaladillo.pedrojoya.pr04.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr04.utils.ResourcesUtils;

public class AvatarActivity extends AppCompatActivity {

    // SEGÚN LOS TESTS EL EXTRA DEBE TENER EL VALOR EXTRA_AVATAR
    public static final String EXTRA_AVATAR_FROM_MAIN ="EXTRA_AVATAR_FROM_MAIN";
    public static final String EXTRA_AVATAR_TO_MAIN="EXTRA_AVATAR_TO_MAIN";
    public final long CAT1_ID=1;
    public final long CAT2_ID=2;
    public final long CAT3_ID=3;
    public final long CAT4_ID=4;
    public final long CAT5_ID=5;
    public final long CAT6_ID=6;

    private Avatar avatarIn;
    private ImageView imgAvatar1;
    private ImageView imgAvatar2;
    private ImageView imgAvatar3;
    private ImageView imgAvatar4;
    private ImageView imgAvatar5;
    private ImageView imgAvatar6;

    private List<Avatar> avatares;
    private TextView lblAvatar1;
    private TextView lblAvatar2;
    private TextView lblAvatar3;
    private TextView lblAvatar4;
    private TextView lblAvatar5;
    private TextView lblAvatar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        setupView();
        getIntentData();
        
        imgAvatar1.setOnClickListener(v -> intentAvatarToMain(0));

        imgAvatar2.setOnClickListener(v -> intentAvatarToMain(1));

        imgAvatar3.setOnClickListener(v -> intentAvatarToMain(2));

        imgAvatar4.setOnClickListener(v -> intentAvatarToMain(3));

        imgAvatar5.setOnClickListener(v -> intentAvatarToMain(4));

        imgAvatar6.setOnClickListener(v -> intentAvatarToMain(5));

        lblAvatar1.setOnClickListener(v -> intentAvatarToMain(0));

        lblAvatar2.setOnClickListener(v -> intentAvatarToMain(1));

        lblAvatar3.setOnClickListener(v -> intentAvatarToMain(2));

        lblAvatar4.setOnClickListener(v -> intentAvatarToMain(3));

        lblAvatar5.setOnClickListener(v -> intentAvatarToMain(4));

        lblAvatar6.setOnClickListener(v -> intentAvatarToMain(5));

    }

    private void intentAvatarToMain(int i) {
        Intent intentOut = new Intent();
        intentOut.putExtra(EXTRA_AVATAR_TO_MAIN, avatares.get(i));
        setResult(RESULT_OK, intentOut);
        finish();
    }

    private void setupView() {
        imgAvatar1=ActivityCompat.requireViewById(this, R.id.imgAvatar1);
        imgAvatar2=ActivityCompat.requireViewById(this, R.id.imgAvatar2);
        imgAvatar3=ActivityCompat.requireViewById(this, R.id.imgAvatar3);
        imgAvatar4=ActivityCompat.requireViewById(this, R.id.imgAvatar4);
        imgAvatar5=ActivityCompat.requireViewById(this, R.id.imgAvatar5);
        imgAvatar6=ActivityCompat.requireViewById(this, R.id.imgAvatar6);

        lblAvatar1=ActivityCompat.requireViewById(this, R.id.lblAvatar1);
        lblAvatar2=ActivityCompat.requireViewById(this, R.id.lblAvatar2);
        lblAvatar3=ActivityCompat.requireViewById(this, R.id.lblAvatar3);
        lblAvatar4=ActivityCompat.requireViewById(this, R.id.lblAvatar4);
        lblAvatar5=ActivityCompat.requireViewById(this, R.id.lblAvatar5);
        lblAvatar6=ActivityCompat.requireViewById(this, R.id.lblAvatar6);


        avatares=Database.getInstance().queryAvatars();
        imgAvatar1.setImageResource(avatares.get(0).getImageResId());
        lblAvatar1.setText(avatares.get(0).getName());
        imgAvatar2.setImageResource(avatares.get(1).getImageResId());
        lblAvatar2.setText(avatares.get(1).getName());
        imgAvatar3.setImageResource(avatares.get(2).getImageResId());
        lblAvatar3.setText(avatares.get(2).getName());
        imgAvatar4.setImageResource(avatares.get(3).getImageResId());
        lblAvatar4.setText(avatares.get(3).getName());
        imgAvatar5.setImageResource(avatares.get(4).getImageResId());
        lblAvatar5.setText(avatares.get(4).getName());
        imgAvatar6.setImageResource(avatares.get(5).getImageResId());
        lblAvatar6.setText(avatares.get(5).getName());
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(EXTRA_AVATAR_FROM_MAIN)){
            avatarIn = intent.getParcelableExtra(EXTRA_AVATAR_FROM_MAIN);
            setColorSelectAvatar(avatarIn.getId());
        }
    }

    private void setColorSelectAvatar(long id) {
        if(id==CAT1_ID){
            imgAvatar1.setColorFilter(R.color.activity_avatar_selected_background);
        }else if(id==CAT2_ID){
            imgAvatar2.setColorFilter(R.color.activity_avatar_selected_background);
        }else if(id==CAT3_ID){
            imgAvatar3.setColorFilter(R.color.activity_avatar_selected_background);
        }else if(id==CAT4_ID){
            imgAvatar4.setColorFilter(R.color.activity_avatar_selected_background);
        }else if(id==CAT5_ID){
            imgAvatar5.setColorFilter(R.color.activity_avatar_selected_background);
        }else if(id==CAT6_ID){
            imgAvatar6.setColorFilter(R.color.activity_avatar_selected_background);
        }
    }


    public static void startForResult(Activity activity, int requestCode,
                                      Avatar avatar) {
        Intent intent = new Intent(activity, AvatarActivity.class);
        intent.putExtra(EXTRA_AVATAR_FROM_MAIN, avatar);
        activity.startActivityForResult(intent, requestCode);
    }

    // DO NO TOUCH
    private void selectImageView(ImageView imageView) {
        imageView.setAlpha(ResourcesUtils.getFloat(this, R.dimen.selected_image_alpha));
    }

    // DO NOT TOUCH
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
