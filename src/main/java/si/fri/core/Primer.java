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

    private String generatedName;

    @Column
    private String name;

    @Column(length = 50)
    private String sequence;

    // not sure if stays
    @Column
    private Orientation orientation;

    private int length;

    @ManyToOne(targetEntity = Freezer.class)
    @JoinColumn(name = "freezer_id", referencedColumnName = "id")
    private Freezer freezer;

    @ManyToOne(targetEntity = Drawer.class)
    @JoinColumn(name = "drawer_id", referencedColumnName = "id")
    private Drawer drawer;

    @ManyToOne(targetEntity = Box.class)
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private Box box;

    @ManyToOne(targetEntity = PositionInReference.class)
    @JoinColumn(name = "positionInReference_id", referencedColumnName = "id")
    private PositionInReference positionInReference;

    @Column
    private double Tm;

    private double optimalTOfAnnealing;

    @ManyToOne(targetEntity = PurificationMethod.class)
    @JoinColumn(name = "purificationMethod_id", referencedColumnName = "id")
    private PurificationMethod purificationMethod;

    private double amountAvailableMikroL;

    private int amountAvailablePacks;

    private AmountAvailablePackSize amountAvailablePackSize;

    @Temporal(TemporalType.DATE)
    private Date date;

    private int lengthOfAmplicone;

    private double storingT;

    private double GCPercent;

    @ManyToOne(targetEntity = Organism.class)
    @JoinColumn(name = "organism_id", referencedColumnName = "id")
    private Organism organism;

    @Column
    private String gen;

    private String ncbiGenId;

    @ManyToOne(targetEntity = HumanGenomBuild.class)
    @JoinColumn(name = "humanGenomBuild_id", referencedColumnName = "id")
    private HumanGenomBuild humanGenomBuild;

    @ManyToOne(targetEntity = Formulation.class)
    @JoinColumn(name = "formulation_id", referencedColumnName = "id")
    private Formulation formulation;

    @ManyToOne(targetEntity = TypeOfPrimer.class)
    @JoinColumn(name = "typeOfPrimer_id", referencedColumnName = "id")
    private TypeOfPrimer typeOfPrimer;

    @Column(length = 50)
    private String sondaSequence;

    @Column(length = 50)
    private String assayId;

    private Size size;

    @ManyToOne(targetEntity = PrimerApplication.class)
    @JoinColumn(name = "primerApplication_id", referencedColumnName = "id")
    private PrimerApplication primerApplication;

    private String applicationComment;

    @ManyToOne(targetEntity = FiveModification.class)
    @JoinColumn(name = "fiveModification_id", referencedColumnName = "id")
    private FiveModification fiveModification;

    @ManyToOne(targetEntity = ThreeModification.class)
    @JoinColumn(name = "threeModification_id", referencedColumnName = "id")
    private ThreeModification threeModification;

    private int concentrationOrdered;

    private ConcentrationOrderedUnit concentrationOrderedUnit;

    private boolean checkSpecifityInBlast;

    private String designerName;

    private String designerPublication;

    private String designerDatabase;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    private String orderedBy;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    private String comment;

    private String document;

    private String analysis;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Primer() {
        // Jackson deserialization
    }

    public Primer(String name, String sequence, Orientation orientation, int length, Freezer freezer, Drawer drawer,
                  Box box, PositionInReference positionInReference, double Tm, double optimalTOfAnnealing,
                  PurificationMethod purificationMethod, double amountAvailableMikroL, int amountAvailablePacks,
                  AmountAvailablePackSize amountAvailablePackSize, Date date, int lengthOfAmplicone, double storingT,
                  double GCPercent, Organism organism, String gen, String ncbiGenId, HumanGenomBuild humanGenomBuild,
                  Formulation formulation, TypeOfPrimer typeOfPrimer, String sondaSequence, String assayId, Size size,
                  PrimerApplication primerApplication, String applicationComment, String generatedName, FiveModification fiveModification,
                  ThreeModification threeModification, int concentrationOrdered, ConcentrationOrderedUnit concentrationOrderedUnit,
                  boolean checkSpecifityInBlast, String designerName, String designerPublication, String designerDatabase,
                  Project project, String orderedBy, Supplier supplier, Manufacturer manufacturer, String comment,
                  String document, String analysis, User user) {
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
        this.amountAvailableMikroL = amountAvailableMikroL;
        this.amountAvailablePacks = amountAvailablePacks;
        this.amountAvailablePackSize = amountAvailablePackSize;
        this.date = date;
        this.lengthOfAmplicone = lengthOfAmplicone;
        this.storingT = storingT;
        this.GCPercent = GCPercent;
        this.organism = organism;
        this.gen = gen;
        this.ncbiGenId = ncbiGenId;
        this.humanGenomBuild = humanGenomBuild;
        this.formulation = formulation;
        this.typeOfPrimer = typeOfPrimer;
        this.sondaSequence = sondaSequence;
        this.assayId = assayId;
        this.size = size;
        this.primerApplication = primerApplication;
        this.applicationComment = applicationComment;
        this.generatedName = generatedName;
        this.fiveModification = fiveModification;
        this.threeModification = threeModification;
        this.concentrationOrdered = concentrationOrdered;
        this.concentrationOrderedUnit = concentrationOrderedUnit;
        this.checkSpecifityInBlast = checkSpecifityInBlast;
        this.designerName = designerName;
        this.designerPublication = designerPublication;
        this.designerDatabase = designerDatabase;
        this.project = project;
        this.orderedBy = orderedBy;
        this.supplier = supplier;
        this.manufacturer = manufacturer;
        this.comment = comment;
        this.document = document;
        this.user = user;
        this.analysis = analysis;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getGeneratedName() {
        return generatedName;
    }

    public void setGeneratedName(String generatedName) {
        this.generatedName = generatedName;
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

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @JsonProperty
    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @JsonProperty
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @JsonProperty
    public Freezer getFreezer() {
        return freezer;
    }

    public void setFreezer(Freezer freezer) {
        this.freezer = freezer;
    }

    @JsonProperty
    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    @JsonProperty
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
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


    public PurificationMethod getPurificationMethod() {
        return purificationMethod;
    }

    public void setPurificationMethod(PurificationMethod purificationMethod) {
        this.purificationMethod = purificationMethod;
    }

    @JsonProperty
    public double getAmountAvailableMikroL() {
        return amountAvailableMikroL;
    }

    public void setAmountAvailableMikroL(double amountAvailableMikroL) {
        this.amountAvailableMikroL = amountAvailableMikroL;
    }

    @JsonProperty
    public int getAmountAvailablePacks() {
        return amountAvailablePacks;
    }


    public void setAmountAvailablePacks(int amountAvailablePacks) {
        this.amountAvailablePacks = amountAvailablePacks;
    }

    @JsonProperty
    public AmountAvailablePackSize getAmountAvailablePackSize() {
        return amountAvailablePackSize;
    }

    public void setAmountAvailablePackSize(AmountAvailablePackSize amountAvailablePackSize) {
        this.amountAvailablePackSize = amountAvailablePackSize;
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

    @JsonProperty
    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    @JsonProperty
    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @JsonProperty
    public String getNcbiGenId() {
        return ncbiGenId;
    }

    public void setNcbiGenId(String ncbiGenId) {
        this.ncbiGenId = ncbiGenId;
    }

    @JsonProperty
    public HumanGenomBuild getHumanGenomBuild() {
        return humanGenomBuild;
    }

    public void setHumanGenomBuild(HumanGenomBuild humanGenomBuild) {
        this.humanGenomBuild = humanGenomBuild;
    }

    @JsonProperty
    public Formulation getFormulation() {
        return formulation;
    }

    public void setFormulation(Formulation formulation) {
        this.formulation = formulation;
    }

    @JsonProperty
    public TypeOfPrimer getTypeOfPrimer() {
        return typeOfPrimer;
    }

    public void setTypeOfPrimer(TypeOfPrimer typeOfPrimer) {
        this.typeOfPrimer = typeOfPrimer;
    }

    @JsonProperty
    public String getSondaSequence() {
        return sondaSequence;
    }

    public void setSondaSequence(String sondaSequence) {
        this.sondaSequence = sondaSequence;
    }

    @JsonProperty
    public String getAssayId() {
        return assayId;
    }

    public void setAssayId(String assayId) {
        this.assayId = assayId;
    }

    @JsonProperty
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @JsonProperty
    public PrimerApplication getPrimerApplication() {
        return primerApplication;
    }

    public void setPrimerApplication(PrimerApplication primerApplication) {
        this.primerApplication = primerApplication;
    }

    @JsonProperty
    public String getApplicationComment() {
        return applicationComment;
    }

    public void setApplicationComment(String applicationComment) {
        this.applicationComment = applicationComment;
    }

    @JsonProperty
    public FiveModification getFiveModification() {
        return fiveModification;
    }

    public void setFiveModification(FiveModification fiveModification) {
        this.fiveModification = fiveModification;
    }

    @JsonProperty
    public ThreeModification getThreeModification() {
        return threeModification;
    }

    public void setThreeModification(ThreeModification threeModification) {
        this.threeModification = threeModification;
    }

    @JsonProperty
    public int getConcentrationOrdered() {
        return concentrationOrdered;
    }

    public void setConcentrationOrdered(int concentrationOrdered) {
        this.concentrationOrdered = concentrationOrdered;
    }

    @JsonProperty
    public ConcentrationOrderedUnit getConcentrationOrderedUnit() {
        return concentrationOrderedUnit;
    }

    public void setConcentrationOrderedUnit(ConcentrationOrderedUnit concentrationOrderedUnit) {
        this.concentrationOrderedUnit = concentrationOrderedUnit;
    }


    public boolean isCheckSpecifityInBlast() {
        return checkSpecifityInBlast;
    }

    public void setCheckSpecifityInBlast(boolean checkSpecifityInBlast) {
        this.checkSpecifityInBlast = checkSpecifityInBlast;
    }

    @JsonProperty
    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    @JsonProperty
    public String getDesignerPublication() {
        return designerPublication;
    }

    public void setDesignerPublication(String designerPublication) {
        this.designerPublication = designerPublication;
    }

    @JsonProperty
    public String getDesignerDatabase() {
        return designerDatabase;
    }

    public void setDesignerDatabase(String designerDatabase) {
        this.designerDatabase = designerDatabase;
    }

    @JsonProperty
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @JsonProperty
    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    @JsonProperty
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @JsonProperty
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @JsonProperty
    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @JsonProperty
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

