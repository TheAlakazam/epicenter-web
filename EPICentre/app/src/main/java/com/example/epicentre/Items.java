package com.example.epicentre;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Items {
    ArrayList<Data> datas = new ArrayList<>();
    Context mainActivity;

    public Items(Context mainActivity) {
        this.mainActivity = mainActivity;
    }

    public ArrayList<Data> Populate()
    {
        datas.add(new Data("Before Earthquake : Preventive measures",
                "Have an earthquake readiness plan.\n" +
                        "\n" +
                        "Earthquake can occur almost at once and there is no way to predict it before it happens. If you live in a location that's prone to earthquakes you must know in advance what to do in case of disaster to ensure your survival. Make sure everyone in your family understands what to do when the quake strikes, no matter where they are.\n" +
                        "\n" +
                        "Learn how to make your house safer.\n" +
                        "\n" +
                        "Consult a professional to learn how to make your home sturdier, such as bolting bookcases to wall studs, installing strong latches on cupboards, and strapping the water heater to wall studs. Store heavy objects on lower shelves and store breakable objects in cabinets with latched doors. Don't hang heavy mirrors or pictures above where people frequently sit or sleep. Store flammable liquids away from potential ignition sources such as water heaters, stoves and furnaces.\n" +
                        "\n" +
                        "Locate a place in each room of the house that you can go to in case of an earthquake.\n" +
                        "\n" +
                        "Collapsing walls, flying glass and falling objects cause most quake-related injuries and deaths. You must locate a spot where nothing is likely to fall on you."
                ,BitmapFactory.decodeResource(mainActivity.getResources(),R.drawable.before)));
        datas.add(new Data("During Earthquake : Following the plan",
                "During the earthquake - Following the plan\n" +
                        "Drop down and take cover near massive and fixed object.\n" +
                        "\n" +
                        "First advice is to stay where you are.Get near a heavy desk, sofa or table and hang on to it. Finding an object that is big enough to compress and still leave a void next to it is essential. This is a place you must think about in your earthquake readiness plan. While you are down you can curl up in the fetal position protecting your head and vital organs.\n" +
                        "\n" +
                        "Stay indoor.\n" +
                        "\n" +
                        "Stay indoors until the shaking stops and you're sure it's safe to exit. Most quake injuries occur as people enter or leave buildings.\n" +
                        "\n" +
                        "Stay away from bookcases or furniture that can fall on you.\n" +
                        "\n" +
                        "The goal is to protect yourself from falling objects and be located near the structural strong points of the room. It is not recommendable to use a doorway for shelter unless it is in close proximity to you and if you know it is a strongly supported, loadbearing doorway. Otherwise you might get crushed by the ceiling above if the doorjamb falls forward or backward or get cut in half if it falls sideways.\n" +
                        "\n" +
                        "Stay away from windows.\n" +
                        "\n" +
                        "Protect yourself from glass that might shatter: windows, mirrors or bottles.\n" +
                        "\n" +
                        "If you are in bed get beside it and cover yourself.\n" +
                        "\n" +
                        "Do not stay in bed and don't get under it. It is safer to roll off the bed and cover with a pillow because a small void will always exist around the bed.\n" +
                        "\n" +
                        "If you are outside, find a clear spot and stay there.\n" +
                        "\n" +
                        "If you are outdoors, find a clear spot away from buildings, trees, and power lines. Drop to the ground. The greatest danger exists directly outside buildings, at exits and alongside exterior walls.\n" +
                        "\n" +
                        "If you are in a car slow down and drive to a clear place. Stay in the car until the shaking stops. Proceed cautiously once the earthquake has stopped. Avoid roads, bridges, or ramps that might have been damaged by the earthquake. If you can't find a clear spot then stop the car and get beside it. Don't stay in it or under it because something can drop on it and crush you.\n" +
                        "\n" +
                        "Don't use the elevators or the stairs. DO NOT use the elevators or run to the stairs. The stairs swing separately from the main part of the building and are a likely part of the building to be damaged."
                ,BitmapFactory.decodeResource(mainActivity.getResources(),R.drawable.during)));
        datas.add(new Data("After Earthquake : Surviving the disaster",
                "Check for injuries, attend to injuries if needed, help ensure the safety of people around you. Put on shoes with heavy soles. Wear gloves.When the quake subsides, don't blindly run out. More shocks may be on the way - perhaps bigger than the first, perhaps smaller.\n" +
                        "\n" +
                        "Check for damage. Avoid elevators and be very wary of stairways, which may have been damaged. If your building is badly damaged you should leave it until it has been inspected by a safety professional\n" +
                        "\n" +
                        "Turn off the gas and water. If you smell or hear a gas leak, get everyone outside and open windows and doors. If you can do it safely, turn off the gas at the meter. Report the leak to the gas company and fire department. Do not use any electrical appliances because a tiny spark could ignite the gas. Open windows and doors for ventilation if you smell gas.\n" +
                        "\n" +
                        "Before using a fire extinguisher, be sure you have a safe exit in case the fire gets out of control. Aim the extinguisher at the base of the fire. Sweep the nozzle from side to side and slightly raise the stream as you sweep.\n" +
                        "\n" +
                        "Unplug major appliances to prevent possible damage. If the power is out, unplug major appliances to prevent possible damage when the power is turned back on. If you see sparks, frayed wires, or smell hot insulation turn off electricity at the main fuse box or breaker. If you will have to step in water to turn off the electricity you should call a professional to turn it off for you.\n" +
                        "\n" +
                        "If trapped under debris. Do not light a match. Do not move about or kick up dust.Cover your mouth with a handkerchief or clothing. Tap on a pipe or wall so rescuers can locate you. Use a whistle if one is available. Shout only as a last resort. Shouting can cause you to inhale dangerous amounts of dust."
                ,BitmapFactory.decodeResource(mainActivity.getResources(),R.drawable.after)));

        return datas;
    }
}


class Data implements Parcelable{
    String Heading;
    String Body;
    Bitmap image;

    public Data(String heading, String body, Bitmap image) {
        Heading = heading;
        Body = body;
        this.image = image;
    }

    public String getHeading() {
        return Heading;
    }

    public String getBody() {
        return Body;
    }

    public Bitmap getImage() {
        return image;
    }

    protected Data(Parcel in) {
        Heading = in.readString();
        Body = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Heading);
        dest.writeString(Body);
        dest.writeParcelable(image, flags);
    }
}