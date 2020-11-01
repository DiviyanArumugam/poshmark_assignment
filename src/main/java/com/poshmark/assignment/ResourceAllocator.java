package com.poshmark.assignment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.poshmark.assignment.model.Temp;
import com.poshmark.assignment.model.input.ResourceInfo;
import com.poshmark.assignment.model.output.ServerOption;
import com.poshmark.assignment.util.PrintExclusionStrategy;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.poshmark.assignment.ResourceAllocatorHelper.*;
import static com.poshmark.assignment.constants.Constants.*;

public class ResourceAllocator {

    private List<Temp> tempList = null;
    private List<Temp> cpuByCostList = null;
    private List<Temp> costList = null;
    private List<Temp> result = new ArrayList<>();

    public static void main(String[] a) throws IOException {
        if (a.length < 3) {
            System.out.println("Invalid arguments !!!");
            return;
        }
        int hours = Integer.parseInt(a[0]);
        int cpus = Integer.parseInt(a[1]);
        Float price = Float.parseFloat(a[2]);
        String result = new ResourceAllocator().process(hours, cpus, price);
        System.out.println(result);
    }

    public String process(int hours, Integer cpusIn, Float priceIn) throws IOException {
        if (hours == 0) {
            return "Invalid input !!!";
        }
        if (cpusIn == null && priceIn == null) {
            return "Invalid input !!!";
        }

        ResourceInfo resourceInfo = loadJsonInfo();

        tempList = loadTemp(resourceInfo);

        Integer cpus = null;
        if (cpusIn != null) {
            cpus = new Integer(cpusIn);
        }
        Float price = null;
        if (priceIn != null) {
            price = new Float(priceIn);
            price = price / hours;
        }

        cpuByCostList = tempList.stream().sorted(Comparator.comparing(Temp::getCpuByCost).reversed()).collect(Collectors.toList());
        costList = tempList.stream().sorted(Temp::compareCost).collect(Collectors.toList());

        getCosts(hours, cpus, price);

        Double costFor1Hour = result.stream().mapToDouble(Temp::getCost).sum();
        if (priceIn != null && costFor1Hour * hours > priceIn) {
            System.out.println("The price limit exceeds and so, unable to allocate servers !!!");
            return "The price limit exceeds and so, unable to allocate servers !!!";
        }

//        result.forEach(item -> System.out.println(item.toString()));
//        System.out.println("Cost for one hour - " + costFor1Hour);
//        System.out.println("Cost for " + hours + " hours - " + costFor1Hour * hours);
//        System.out.println("Given Cost - " + priceIn);
//        System.out.println("Sum of CPU - " + result.stream().mapToInt(Temp::getCpus).sum());
//        System.out.println("Given CPUs - " + cpusIn);
//        System.out.println("Count of Servers - " + result.size());

        List<ServerOption> output = createOutput(result, hours);

        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new PrintExclusionStrategy())
                .create();
        return gson.toJson(output);
    }

    private List<ServerOption> createOutput(List<Temp> result, Integer hours) {
        Map<String, ServerOption> map = new HashMap<>();
        for (Temp temp: result) {
            ServerOption serverOption = map.get(temp.getRegion());
            if (serverOption == null) {
                serverOption = new ServerOption();
                serverOption.setRegion(temp.getRegion());
                map.put(temp.getRegion(), serverOption);
            }
            Map<String, Integer> mapTemp = serverOption.getServerMap();
            if (mapTemp.containsKey(temp.getServerType())) {
                mapTemp.put(temp.getServerType(), mapTemp.get(temp.getServerType()) + serverToCpuMap.get(temp.getServerType()));
                serverOption.setTotalCost(serverOption.getTotalCost() + (temp.getCost() * hours));
            } else {
                mapTemp.put(temp.getServerType(), serverToCpuMap.get(temp.getServerType()));
                serverOption.setTotalCost(temp.getCost() * hours);
            }
        }

        for(ServerOption serverOption: map.values()) {
            serverOption.setTotalCostStr("$" + serverOption.getTotalCost());
            StringBuilder stringBuilder = new StringBuilder();
            Collection<Map.Entry<String, Integer>> entries = serverOption.getServerMap().entrySet();
            for(Map.Entry<String, Integer> entry: entries) {
                serverOption.getServerList().add(stringBuilder.append("(\"").append(entry.getKey()).append("\", ").append(entry.getValue()).append(")").toString());
            }
        }

        return new ArrayList<>(map.values());
    }

    private void getCosts(int hours, Integer cpus, Float price) {
        if (cpus != null) {
            while(cpus > 0) {
                Temp temp = getNextServerByCpu(cpus);
                if (temp == null) {
                    break;
                }
                cpus -= temp.getCpus();
                result.add(temp);
            }
        } else if (price != null) {
            while(price > 0) {
                Temp temp = getNextServerByPrice(price);
                if (temp == null) {
                    break;
                }
                price -= temp.getCost();
                result.add(temp);
            }
        }
    }

    private Temp getNextServerByCpu(int cpus) {
        for (Temp temp: cpuByCostList) {
            if (temp.getCpus() <= cpus) {
                return temp;
            }
        }
        return null;
    }

    private Temp getNextServerByPrice(Float price) {
        for (Temp temp: costList) {
            if (temp.getCost() <= price) {
                return temp;
            }
        }
        return null;
    }


}
