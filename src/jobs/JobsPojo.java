
package jobs;

public class JobsPojo {
    private int id;
    private String name;
    private String position;
    private String applicationDate;
    private String response;
    private String interview;
    private String jobOffer;
    
    public JobsPojo(){
        
    }
    
    public JobsPojo(int id, String name, String position, String applicationDate, String response, String interview, String jobOffer) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.applicationDate = applicationDate;
        this.response = response;
        this.interview = interview;
        this.jobOffer = jobOffer;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getResponse() {
        return response;
    }

    public String getInterview() {
        return interview;
    }

    public String getJobOffer() {
        return jobOffer;
    }
    
    
}
