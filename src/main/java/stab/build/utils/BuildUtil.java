package stab.build.utils;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import stab.build.BuildProperties;
import stab.build.profile.BuildProfile;
import stab.build.tasks.AbstractBuildTask;
import stab.build.tasks.BuildTask;

import java.util.*;

public class BuildUtil {

    public static BuildProfile getProfile(String profile) {
        try {
            return (BuildProfile) Class.forName(profile).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getDefaultConfiguration() {
        Configuration result = new BaseConfiguration();
        result.addProperty(BuildProperties.DATE, getCurrentDate());

        return result;
    }

    private static Date getCurrentDate() {
        String dateString = System.getenv("DATE");
        Date result = null;
        if (dateString != null) {
            result = DateUtil.getDate(dateString.replace("-", ""));
        }
        return result;
    }

    public static Configuration getConfiguration(String[] properties) {
        Configuration result = getDefaultConfiguration();

        if (properties != null) {
            for (String str : properties) {
                int index = str.indexOf("=");
                if (index > 0) {
                    String key = str.substring(0, index);
                    String val = str.substring(index + 1, str.length());
                    result.setProperty(key, val);
                } else {
                    result.setProperty(str, true);
                }
            }
        }

        return result;
    }

    public static Date getDateOffset(Date date, int offset) {
        Date result = null;

        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -offset);
            result = calendar.getTime();
        }

        return result;
    }

    public static Date getDateOffset(String date, int offset) {
        return getDateOffset(DateUtil.getDate(date), offset);
    }

    public static List<BuildTask> getTasks(AbstractBuildTask... tasks) {
        List<BuildTask> result = new ArrayList<BuildTask>();
        for (AbstractBuildTask task : tasks) {
            result.add(task);
        }
        return result;
    }
}
