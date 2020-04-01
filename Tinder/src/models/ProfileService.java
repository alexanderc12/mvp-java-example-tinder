package models;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import persistence.FileIdService;
import persistence.FileManager;

public class ProfileService {

	private static final String DOT_SEPARATOR = ".";
	private static final String IMG_PATH = "./db/img/";
	private static final String ERROR_PROFILE_NOT_FOUND = "Profile not found!";
	private ArrayList<Profile> profileList;

	public ProfileService() {
		profileList = FileManager.read();
	}
	
	public static Profile createProfile(String name, String file) throws IOException, URISyntaxException, SAXException,
	ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		long id = FileIdService.readIdFile() + 1;
		FileIdService.writeIdFile(id);
		String path = IMG_PATH + id + file.substring(file.lastIndexOf(DOT_SEPARATOR), file.length());
		Files.copy(Paths.get(file), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		return new Profile(id, name, path);
	}
	
	public void addProfile(Profile profile) {
		profileList.add(profile);
	}

	public ArrayList<Profile> getProfileList() {
		return profileList;
	}

	public Profile search(int id) throws Exception{
		for (Profile profile : profileList) {
			if (profile.getId() == id) {
				return profile;
			}
		}
		throw new Exception(ERROR_PROFILE_NOT_FOUND);
	}

	public void like(int id) throws Exception {
		search(id).setStatus(Status.LIKE);
	}

	public void dislike(int id) throws Exception {
		search(id).setStatus(Status.DISLIKE);
	}
}