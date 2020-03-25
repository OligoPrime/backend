package si.fri.core;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "primers")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.Primer.findAll",
                        query = "SELECT p FROM Primer p"
                )
        })
public class Primer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(nullable = false, length = 50)
    private String sequence;

    @Column(nullable = false)
    private Orientation orientation;

    private int length;

    @Column(nullable = false)
    private String freezer;

    @Column(nullable = false)
    private int drawer;

    @Column(nullable = false)
    private String box;

    @ManyToOne(targetEntity = PositionInReference.class)
    @JoinColumn(name = "positionInReference_id", referencedColumnName = "id", nullable = false)
    private PositionInReference positionInReference;

    @Column(nullable = false)
    private double Tm;

    private double optimalTOfAnnealing;

    @ManyToOne(targetEntity = PurificationMethod.class)
    @JoinColumn(name = "purificationMethod_id", referencedColumnName = "id", nullable = false)
    private PurificationMethod purificationMethod;

    private double amountAvailable;

    private AmountAvailableUnit amountAvailableUnit;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int lengthOfAmplicone;

    private double storingT;

    @Column(nullable = false)
    private double GCPercent;

    public Primer() {
        // Jackson deserialization
    }

    public Primer(String name, String sequence, Orientation orientation, int length, String freezer, int drawer,
                  String box, PositionInReference positionInReference, double Tm, double optimalTOfAnnealing,
                  PurificationMethod purificationMethod, double amountAvailable, AmountAvailableUnit amountAvailableUnit,
                  Date date, int lengthOfAmplicone, double storingT, double GCPercent) {
        this.name = name;
        this.sequence = sequence;
        this.orientation = orientation;
        this.length = length;
        this.freezer = freezer;
        this.drawer = drawer;
        this.box = box;
        this.positionInReference = positionInReference;
        this.Tm = Tm;
        this.optimalTOfAnnealing = optimalTOfAnnealing;
        this.purificationMethod = purificationMethod;
        this.amountAvailable = amountAvailable;
        this.amountAvailableUnit = amountAvailableUnit;
        this.date = date;
        this.lengthOfAmplicone = lengthOfAmplicone;
        this.storingT = storingT;
        this.GCPercent = GCPercent;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getSequence() {
        return sequence;
    }

    public void setSequence() {
        this.sequence = sequence;
    }

    @JsonProperty
    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation() {
        this.orientation = orientation;
    }

    @JsonProperty
    public int getLength() {
        return length;
    }

    public void setLength() {
        this.length = length;
    }

    @JsonProperty
    public String getFreezer() {
        return freezer;
    }

    public void setFreezer() {
        this.freezer = freezer;
    }

    @JsonProperty
    public int getDrawer() {
        return drawer;
    }

    public void setDrawer() {
        this.drawer = drawer;
    }

    @JsonProperty
    public String getBox() {
        return box;
    }

    public void setBox() {
        this.box = box;
    }

    @JsonProperty
    public PositionInReference getPositionInReference() {
        return positionInReference;
    }

    public void setPositionInReference(PositionInReference positionInReference) {
        this.positionInReference = positionInReference;
    }

    @JsonProperty
    public double getTm() {
        return Tm;
    }

    public void setTm(double tm) {
        Tm = tm;
    }

    @JsonProperty
    public double getOptimalTOfAnnealing() {
        return optimalTOfAnnealing;
    }

    public void setOptimalTOfAnnealing(double optimalTOfAnnealing) {
        this.optimalTOfAnnealing = optimalTOfAnnealing;
    }

    @JsonProperty
    public PurificationMethod getPurificationMethod() {
        return purificationMethod;
    }

    public void setPurificationMethod(PurificationMethod purificationMethod) {
        this.purificationMethod = purificationMethod;
    }

    @JsonProperty
    public double getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(double amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    @JsonProperty
    public AmountAvailableUnit getAmountAvailableUnit() {
        return amountAvailableUnit;
    }

    public void setAmountAvailableUnit(AmountAvailableUnit amountAvailableUnit) {
        this.amountAvailableUnit = amountAvailableUnit;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty
    public int getLengthOfAmplicone() {
        return lengthOfAmplicone;
    }

    public void setLengthOfAmplicone(int lengthOfAmplicone) {
        this.lengthOfAmplicone = lengthOfAmplicone;
    }

    @JsonProperty
    public double getStoringT() {
        return storingT;
    }

    public void setStoringT(double storingT) {
        this.storingT = storingT;
    }

    @JsonProperty
    public double getGCPercent() {
        return GCPercent;
    }

    public void setGCPercent(double GCPercent) {
        this.GCPercent = GCPercent;
    }
}

