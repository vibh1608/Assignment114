package com.example.user.assignment114;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //creating object of DataBaseHelper class to fetch data
    DataBaseHelper mDataBaseHelper;

    //creating objects of listview to show on user interface
    public ListView identity;
    public ListView firstname;
    public ListView lastname;


    //creating arraylist of data that has to be shown on the user interface
    public ArrayList<Friends> mFriends;
    public ArrayList<String> mId;
    public ArrayList<String> first;
    public ArrayList<String> last;

    //storing temporary data in string format
    String[] first_name=new String[]
            {
                    "AKASH","ZUBAIR","ASHUTOSH","NISCHAY","PRASOON","MILTON","AMIT"
            };

    String[] last_name=new String[]
            {
                    "DHAYAL","AHMED","HATHIDARA","PANDEY","MISHRA","KUMAR","SHARMA"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding objects with their respective ID's in UI
        identity= (ListView) findViewById(R.id.id);
        firstname=(ListView) findViewById(R.id.first_name);
        lastname=(ListView)findViewById(R.id.last_name);

        //creating Arraylist
        mFriends = new ArrayList<>();
        mId = new ArrayList<>();
        first = new ArrayList<>();
        last = new ArrayList<>();

        //binding object of DataBaseHelper class with DataBase
        mDataBaseHelper = new DataBaseHelper(MainActivity.this,Constants.TABLE_NAME,null,Constants.DATABASE_VERSION);

        if(mDataBaseHelper.getRowCountFromTable()==0)
        {
            //TODO:INSERT DATA TO THE TABLE
            insertRecordInTable();
        }

        //storing data from DataBase to arrayList object
        mFriends = mDataBaseHelper.getAllFriends();

        //setting Title to ID list view
        mId.add("ID");

        //adding ID of each friend to arrayList object mID
        for(int j=0;j<mFriends.size();j++)
        {
            mId.add(mFriends.get(j).getId());
        }

        //setting adapter to the list ID
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,mId);
        identity.setAdapter(adapter);


        //setting Title to Firstname list view
        first.add("First Name");

        //adding ID of each friend to arrayList object firstname
        for(int j=0;j<mFriends.size();j++)
        {
            first.add(mFriends.get(j).getFirst_name());
        }

        //setting adapter to the list firstname
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,first);
        firstname.setAdapter(adapter1);


        //setting Title to LastName list view
        last.add("Last Name");

        //adding ID of each friend to arrayList object lastname
        for(int j=0;j<mFriends.size();j++)
        {
            last.add(mFriends.get(j).getLast_name());
        }

        //setting adapter to the list lastname
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,last);
        lastname.setAdapter(adapter2);

    }

    //method to insert ID's , First name and Last name in respective list
    private void insertRecordInTable() {
        for(int i=0;i<first_name.length;i++)
        {

            //creating object of contentView to access data of DataBase Table
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.FIRST_NAME,first_name[i]);
            contentValues.put(Constants.LAST_NAME,last_name[i]);
            mDataBaseHelper.insertFriend(contentValues);
        }
    }
}
