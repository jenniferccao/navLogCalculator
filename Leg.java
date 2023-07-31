import java.text.DecimalFormat;

public class Leg {

    // instance variables
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // inputs
    private int windDir;
    private double windVel;
    private int temp;
    private int iAlt;
    private double altSet;
    private int track;
    private int tas;
    private int var;
    private double fuelPH;
    private int dist;
    
    
    // outputs
    private int pAlt;
    private int dAlt;
    private int wca;
    private int hdgTru;
    private int hdgMag;
    private int gndSpd;
    private double ete;
    private double fuelReq;

    public Leg(int windDir, double windVel, int temp, int iAlt, double altSet, int track, int tas, int var, double fuelPH, int dist) {
        this.windDir = windDir;
        this.windVel = windVel;
        this.temp = temp;
        this.iAlt = iAlt;
        this.altSet = altSet;
        this.track = track;
        this.tas = tas;
        this.var = var;
        this. fuelPH = fuelPH;
        this.dist = dist;

        calcAlts();
        calcHdgs();
        calcTime();
    }

    private void calcAlts() {
        double isa = 15 - (iAlt/1000.0 * 1.98);
        pAlt = (int) ((29.92 - altSet) * 1000 + iAlt);
        dAlt = (int) (pAlt + (120 * (temp - isa)));
    }

    private void calcHdgs() {

        gndSpd = (int) Math.round(windVel * Math.abs(Math.cos((windDir-track) * Math.PI/180)) + Math.sqrt(Math.pow(tas, 2) - Math.pow(windVel, 2) * Math.pow(Math.abs(Math.sin((windDir-track) * Math.PI/180)) , 2)));

        wca = (int) (Math.round(((windVel / tas) * Math.sin((windDir - track) * Math.PI / 180) * (180 / Math.PI))));

        hdgTru = track + wca;

        if (hdgTru >= 360) {
            hdgTru = hdgTru - 360;
        } else if (hdgTru < 0) {
            hdgTru = hdgTru + 360;
        }

        hdgMag = hdgTru + var;

        if (hdgTru >= 360) {
            hdgTru = hdgTru - 360;
        } else if (hdgTru < 0) {
            hdgTru = hdgTru + 360;
        }
    }

    private void calcTime() {
        ete = ((double)dist / (double)gndSpd * 60.0);
        fuelReq = (ete / 60 * fuelPH);
    }

    // work in progress
    private void calcClimb() {

    }


    public int getDist() { return dist; }

    public int getPAlt() { return pAlt; }
    public int getDAlt() { return dAlt; }
    public int getHdgTru() { return hdgTru; }
    public int getHdgMag() { return hdgMag; }
    public int getGndSpd() { return gndSpd; }
    public double getEte() { return ete; }
    public double getFuelReq() { return fuelReq; }

    public String toString() {
        String str = ("Pressure Altitude: " + pAlt + "\nDensity Altitude: " + dAlt
                    + "\nWind Correction Angle: " + wca + "\nTrue Heading: " + hdgTru + "\nMagnetic Heading: " + hdgMag
                    + "\nGroundspeed: " + gndSpd + "\nETE: " + df.format(ete) + "\nFuel Required: " + df.format(fuelReq));

        return str;
    }


}
