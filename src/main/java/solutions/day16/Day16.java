package solutions.day16;

import reader.Reader;

import java.util.*;

public class Day16 {

    private final String FILENAME = "day16.txt";
    private Map<String, List<Integer>> availableValues = new HashMap<>();
    private List<Integer> yourTicket = new ArrayList<>();
    private List<List<Integer>> nearbyTickets = new ArrayList<>();

    private List<Integer> invalidValues = new ArrayList<>();



    public Day16() {
        prepareData();
    }

    private void prepareData() {
        List<String> file = Reader.readFileLineByLine(FILENAME);

        int yourTicketIndex = 0;
        int nearbyTiketsIndex = 0;

        for (int i = 0; i < file.size(); i++){
            if( file.get(i).isEmpty()){
                yourTicketIndex = i+2;
                nearbyTiketsIndex = i+5;
                break;
            }
            else {
                String[] nameValue = file.get(i).split(":");
                String name = nameValue[0];

                String[] values = nameValue[1].replace(" ", "").split("or");

                String[] val12 = values[0].split("-");
                String[] val34 = values[1].split("-");

                this.availableValues.put(name, Arrays.asList(
                        Integer.parseInt(val12[0]),
                        Integer.parseInt(val12[1]),
                        Integer.parseInt(val34[0]),
                        Integer.parseInt(val34[1])
                ));
            }
        }

        String[] yourTicket = file.get(yourTicketIndex).split(",");
        for(int j = 0; j < yourTicket.length; j++){
            this.yourTicket.add(Integer.valueOf(yourTicket[j]));
        }

        for(int k = nearbyTiketsIndex; k < file.size(); k++){
            String[] ticket = file.get(k).split(",");
            List<Integer> ticketValues = new ArrayList<>();
            for(String t: ticket){
                ticketValues.add(Integer.valueOf(t));
            }
            this.nearbyTickets.add(ticketValues);
        }
    }


    public long task1() {

        for(List<Integer> ticket: nearbyTickets){
            for(Integer val: ticket){
                if(!checkValue(val)){
                    this.invalidValues.add(val);
                }
            }

        }
        Integer sum = invalidValues.stream()
                .reduce(0, (integer, integer2) -> integer + integer2);

        return sum;
    }


    public long task2() {

        Set<Integer> invalidTickets = new HashSet<>();

        for( int i = 0; i < nearbyTickets.size(); i++){
            for(Integer val: nearbyTickets.get(i)){
                if(!checkValue(val)){
                    invalidTickets.add(i);
                }
            }
        }

        List<List<Integer>> validTicket = new ArrayList<>();
        validTicket.add(this.yourTicket);


        for(int j = 0; j < nearbyTickets.size(); j++){
            if(!invalidTickets.contains(j)){
                validTicket.add(nearbyTickets.get(j));
            }
        }


        Map<String, Set<Integer>> availablePositions = new HashMap<>();
        int size = nearbyTickets.get(0).size();

        for(String s: availableValues.keySet()){
            Set<Integer> positionNumbers = new HashSet<>();
            for(int k = 0; k < size; k++){
                positionNumbers.add(k);
            }

            availablePositions.put(s, positionNumbers);
        }


        for(int m = 0; m < size; m++) {

            for (List<Integer> ticket : validTicket) {
                for(Map.Entry<String, List<Integer>> availableValue: availableValues.entrySet()){
                    Integer val = ticket.get(m);
                    if(!(val >= availableValue.getValue().get(0) && val <= availableValue.getValue().get(1) ||
                            val >= availableValue.getValue().get(2) && val <= availableValue.getValue().get(3))){

                        availablePositions.get(availableValue.getKey()).remove(m);
                    }
                }
            }
        }


        while(!foundAllPosition(availablePositions)){

            for(Set<Integer> pos: availablePositions.values()){
                if(pos.size()==1){

                    Integer val = pos.iterator().next();

                    for(Map.Entry<String, Set<Integer>> p: availablePositions.entrySet()){
                        if(p.getValue().size() > 1){

                            p.getValue().remove(val);
                        }
                    }
                }
            }
        }


        long result = 1;
        for(Map.Entry<String, Set<Integer>> position: availablePositions.entrySet()){

            if(position.getKey().contains("departure")){
                result *= this.yourTicket.get(position.getValue().iterator().next());
            }
        }

        return result;
    }

    private boolean foundAllPosition(Map<String, Set<Integer>> availablePositions) {
        for(Set<Integer> pos: availablePositions.values()){
            if(pos.size()>1) return false;
        }

        return true;
    }


    private boolean checkValue(int val){
        for(List<Integer> availableValues: availableValues.values()){
            if(val >= availableValues.get(0) && val <= availableValues.get(1) ||
            val >= availableValues.get(2) && val <= availableValues.get(3)){
                return true;
            }
        }

        return false;
    }

}
