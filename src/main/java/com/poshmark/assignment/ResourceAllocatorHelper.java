package com.poshmark.assignment;

import com.google.gson.Gson;
import com.poshmark.assignment.model.Temp;
import com.poshmark.assignment.model.input.ResourceCostInfo;
import com.poshmark.assignment.model.input.ResourceInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.poshmark.assignment.constants.Constants.*;

public class ResourceAllocatorHelper {

    private static Gson gson = new Gson();

    public static ResourceInfo loadJsonInfo() throws IOException {
        File file = new File(ResourceAllocator.class.getClassLoader().getResource("resource.json").getFile());
        String fileContent = getFileContentAsString(file.toPath());
        ResourceInfo resourceInfo = gson.fromJson(fileContent, ResourceInfo.class);
        return resourceInfo;
    }

    private static String getFileContentAsString(Path filePath) throws IOException {
        if(filePath == null) return null;
        String fileContent = new String(Files.readAllBytes(filePath));
        return fileContent;
    }

    public static List<Temp> loadTemp(ResourceInfo resourceInfo) {
        List<Temp> result = new ArrayList<>();
        ResourceCostInfo resourceCostInfo = resourceInfo.getUsEast();
        createTemp(result, resourceCostInfo, "us-east");
        resourceCostInfo = resourceInfo.getUsWest();
        createTemp(result, resourceCostInfo, "us-west");
        resourceCostInfo = resourceInfo.getAsia();
        createTemp(result, resourceCostInfo, "asia");
        return result;
    }

    private static void createTemp(List<Temp> list, ResourceCostInfo resourceCostInfo, String region) {
        loadTempForAServerType(list, region, resourceCostInfo, SERVER_LARGE);
        loadTempForAServerType(list, region, resourceCostInfo, SERVER_XLARGE);
        loadTempForAServerType(list, region, resourceCostInfo, SERVER_2XLARGE);
        loadTempForAServerType(list, region, resourceCostInfo, SERVER_4XLARGE);
        loadTempForAServerType(list, region, resourceCostInfo, SERVER_8XLARGE);
        loadTempForAServerType(list, region, resourceCostInfo, SERVER_10XLARGE);
    }

    private static void loadTempForAServerType(List<Temp> list, String region, ResourceCostInfo resourceCostInfo, String serverType) {
        if (resourceCostInfo.getCost(serverType) != null) {
            Temp temp;
            temp = new Temp();
            temp.setRegion(region);
            temp.setServerType(serverType);
            temp.setCost(resourceCostInfo.getCost(serverType));
            temp.setCpus(serverToCpuMap.get(serverType));
            list.add(temp);
        }
    }

}
