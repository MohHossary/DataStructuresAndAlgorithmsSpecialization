import java.io.IOException;
import java.util.*;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_times_ = new LinkedList<>();

    }

    public Response Process(Request request) {
        //simulation phase:
        //================
        // as we received a request at time t, we already reached time t
        // drop all packets (finish times) that have ended already (if any)
        while (!finish_times_.isEmpty() && request.arrival_time >= finish_times_.getFirst())
            finish_times_.removeFirst();

        //scheduling phase:
        //===================
        //if buffer is full
        if (finish_times_.size() >= size_)
        //  return DROPPED
            return new Response(true, -1);
        //else
        else {

        //  enqueue packet finish time
            int start_time = finish_times_.isEmpty() ? request.arrival_time : finish_times_.getLast();
            finish_times_.add(start_time + request.process_time);
        // return response
            return new Response(false, start_time);
        }




//        if (! (finish_times_.size() > 1)) {
//            if (finish_times_.size() < size_ + 1) {
//                int start_time;
//                try {
//                    start_time = finish_times_.getLast();
//                } catch (NoSuchElementException e) {
//                    start_time = 0;
//                }
//                finish_times_.add(request.process_time + start_time);
//                return new Response(false, finish_times_.get(finish_times_.size() - 3));
//            } else
//                return new Response(true, -1);
//        } else {
//            return new Response(false, Math.max(request.arrival_time, finish_times_.getFirst()));
//        }
    }

    private int size_;
    private LinkedList<Integer> finish_times_;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
