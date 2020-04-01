package persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.Profile;
import models.Status;

public class FileManager {

	private static final String TAG_LIST = "list";
	private static final String TAG_STATUS = "status";
	private static final String TAG_IMG = "img";
	private static final String TAG_NAME = "name";
	private static final String TAG_ID = "id";
	private static final String DB_PATH = "./db/list.json";

	@SuppressWarnings("unchecked")
	public static void write(ArrayList<Profile> profiles) {
		JSONObject jsonfile = new JSONObject();
		JSONArray list = new JSONArray();
		for (Profile profile : profiles) {
			JSONObject jsonProfile = new JSONObject();
			jsonProfile.put(TAG_ID, profile.getId());
			jsonProfile.put(TAG_NAME, profile.getName());
			jsonProfile.put(TAG_IMG, profile.getImgPath());
			jsonProfile.put(TAG_STATUS, profile.getStatus().toString());
			list.add(jsonProfile);
		}
		jsonfile.put(TAG_LIST, list);
		try (FileWriter file = new FileWriter(DB_PATH, false)) {
			file.write(jsonfile.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Profile> read() {
		ArrayList<Profile> list = new ArrayList<Profile>();
		JSONParser parser = new JSONParser();
		try (Reader reader = new FileReader(DB_PATH)) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			JSONArray profiles = (JSONArray) jsonObject.get(TAG_LIST);
			Iterator<JSONObject> iterator = profiles.iterator();
			while (iterator.hasNext()) {
				JSONObject profile = (JSONObject) iterator.next();
				list.add(new Profile((Long) profile.get(TAG_ID),
						(String)profile.get(TAG_NAME), (String)profile.get(TAG_IMG),
						Status.valueOf((String)profile.get(TAG_STATUS))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
}