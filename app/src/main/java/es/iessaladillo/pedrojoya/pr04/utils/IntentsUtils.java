package es.iessaladillo.pedrojoya.pr04.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

import es.iessaladillo.pedrojoya.pr04.R;

public class IntentsUtils {

    public static boolean existAppToOpen(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> appList =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return appList.size() > 0;
    }

    public static Intent intentEmail(String email){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        return intent;
    }

    public static Intent intentCallPhone(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phoneNumber));
        return intent;
    }

    public static Intent intentAdress(String address){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.google.android.apps.maps");
        intent.setData(Uri.parse("geo:0,0?q="+address));
        return intent;
    }

    public static Intent intentWebSearch(String url){
        if (!url.substring(0,7).equals("http://") && !url.substring(0,8).equals("https://")) {
            url="http://"+url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }
}
