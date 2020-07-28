package si.fri.core;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import si.fri.core.primer_enums.*;
import si.fri.core.primer_foreign_tables.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "primers")
@NamedQueries(
        {
                @NamedQuery(
                        name = "si.fri.core.Primer.findAll",
                        query = "SELECT p FROM Primer p WHERE p.deleted = false"
                ),
                @NamedQuery(
                        name = "si.fri.core.Primer.findWanted",
                        query = "SELECT p FROM Primer p WHERE p.orderStatus = 'WANTED' AND p.deleted = false"
                ),
                @NamedQuery(
                        name = "si.fri.core.Primer.findOrdered",
                        query = "SELECT p FROM Primer p WHERE p.orderStatus = 'ORDERED' AND p.deleted = false"
                ),
                @NamedQuery(
                        name = "si.fri.core.Primer.findReceived",
                        query = "SELECT p FROM Primer p WHERE p.orderStatus = 'RECEIVED' AND p.deleted = false"
                ),
        })
@SQLDelete(sql = "UPDATE primers SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Primer {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String generatedName;

    @Column(nullable = false)
    private String name;

    @Column(length = 50)
    private String sequence;

    @Enumerated(EnumType.STRING)
    private Orientation orientation;

    private Integer length;

    @ManyToOne(targetEntity = Freezer.class)
    @JoinColumn(name = "freezer_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "freezer")
    @JsonIdentityReference(alwaysAsId = true)
    private Freezer freezer;

    @ManyToOne(targetEntity = Drawer.class)
    @JoinColumn(name = "drawer_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "drawer")
    @JsonIdentityReference(alwaysAsId = true)
    private Drawer drawer;

    @ManyToOne(targetEntity = Box.class)
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "box")
    @JsonIdentityReference(alwaysAsId = true)
    private Box box;

    @ManyToOne(targetEntity = PositionInReference.class)
    @JoinColumn(name = "positionInReference_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "positionInReference")
    @JsonIdentityReference(alwaysAsId = true)
    private PositionInReference positionInReference;

    private Double Tm;

    private Double optimalTOfAnnealing;

    @ManyToOne(targetEntity = PurificationMethod.class)
    @JoinColumn(name = "purificationMethod_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "purificationMethod")
    @JsonIdentityReference(alwaysAsId = true)
    private PurificationMethod purificationMethod;

    private Double amountAvailable;

    private Integer amountAvailablePacks;

    @Enumerated(EnumType.STRING)
    private AmountAvailablePackType amountAvailablePackType;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Integer lengthOfAmplicone;

    private String storingT;

    private Double GCPercent;

    @ManyToOne(targetEntity = Organism.class)
    @JoinColumn(name = "organism_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "organism")
    @JsonIdentityReference(alwaysAsId = true)
    private Organism organism;

    @ManyToOne(targetEntity = Gen.class)
    @JoinColumn(name = "gen_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "gen")
    @JsonIdentityReference(alwaysAsId = true)
    private Gen gen;

    @ManyToOne(targetEntity = NcbiGenId.class)
    @JoinColumn(name = "ncbiGenId_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ncbiGenId")
    @JsonIdentityReference(alwaysAsId = true)
    private NcbiGenId ncbiGenId;

    @ManyToOne(targetEntity = HumanGenomBuild.class)
    @JoinColumn(name = "humanGenomBuild_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "humanGenomBuild")
    @JsonIdentityReference(alwaysAsId = true)
    private HumanGenomBuild humanGenomBuild;

    @ManyToOne(targetEntity = Formulation.class)
    @JoinColumn(name = "formulation_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "formulation")
    @JsonIdentityReference(alwaysAsId = true)
    private Formulation formulation;

    @ManyToOne(targetEntity = TypeOfPrimer.class)
    @JoinColumn(name = "typeOfPrimer_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "typeOfPrimer")
    @JsonIdentityReference(alwaysAsId = true)
    private TypeOfPrimer typeOfPrimer;

    @Column(length = 50)
    private String sondaSequence;

    @Column(length = 50)
    private String assayId;

    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToOne(targetEntity = PrimerApplication.class)
    @JoinColumn(name = "primerApplication_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "primerApplication")
    @JsonIdentityReference(alwaysAsId = true)
    private PrimerApplication primerApplication;

    private String applicationComment;

    @ManyToOne(targetEntity = FiveModification.class)
    @JoinColumn(name = "fiveModification_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fiveModification")
    @JsonIdentityReference(alwaysAsId = true)
    private FiveModification fiveModification;

    @ManyToOne(targetEntity = ThreeModification.class)
    @JoinColumn(name = "threeModification_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "threeModification")
    @JsonIdentityReference(alwaysAsId = true)
    private ThreeModification threeModification;

    private Double concentrationOrdered;

    @Enumerated(EnumType.STRING)
    private ConcentrationOrderedUnit concentrationOrderedUnit;

    private Boolean checkSpecifityInBlast;

    @ManyToOne(targetEntity = DesignerName.class)
    @JoinColumn(name = "designerName_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "designerName")
    @JsonIdentityReference(alwaysAsId = true)
    private DesignerName designerName;

    @ManyToOne(targetEntity = DesignerPublication.class)
    @JoinColumn(name = "designerPublication_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "designerPublication")
    @JsonIdentityReference(alwaysAsId = true)
    private DesignerPublication designerPublication;

    @ManyToOne(targetEntity = DesignerDatabase.class)
    @JoinColumn(name = "designerDatabase_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "designerDatabase")
    @JsonIdentityReference(alwaysAsId = true)
    private DesignerDatabase designerDatabase;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "project")
    @JsonIdentityReference(alwaysAsId = true)
    private Project project;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "supplier")
    @JsonIdentityReference(alwaysAsId = true)
    private Supplier supplier;

    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "manufacturer")
    @JsonIdentityReference(alwaysAsId = true)
    private Manufacturer manufacturer;

    private String comment;

    private String document;

    private String analysis;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(targetEntity = ThreeQuencher.class)
    @JoinColumn(name = "threeQuencher_id", referencedColumnName = "id", nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "threeQuencher")
    @JsonIdentityReference(alwaysAsId = true)
    private ThreeQuencher threeQuencher;

    @ManyToOne(targetEntity = FiveDye.class)
    @JoinColumn(name = "fiveDye_id", referencedColumnName = "id", nullable = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "fiveDye")
    @JsonIdentityReference(alwaysAsId = true)
    private FiveDye fiveDye;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    @ElementCollection
    private Set<Long> linked;

    @JsonIgnore
    private boolean deleted;

    public Primer() {
        // Jackson deserialization
    }

    public Primer(String name, String sequence, Orientation orientation, Freezer freezer, Drawer drawer,
                  Box box, PositionInReference positionInReference, Double Tm, Double optimalTOfAnnealing,
                  PurificationMethod purificationMethod, Double amountAvailable, Integer amountAvailablePacks,
                  AmountAvailablePackType amountAvailablePackType, Integer lengthOfAmplicone, String storingT,
                  Double GCPercent, Organism organism, Gen gen, NcbiGenId ncbiGenId, HumanGenomBuild humanGenomBuild,
                  Formulation formulation, TypeOfPrimer typeOfPrimer, String sondaSequence, String assayId, Size size,
                  PrimerApplication primerApplication, String applicationComment, FiveModification fiveModification,
                  ThreeModification threeModification, Double concentrationOrdered, ConcentrationOrderedUnit concentrationOrderedUnit,
                  Boolean checkSpecifityInBlast, DesignerName designerName, DesignerPublication designerPublication,
                  DesignerDatabase designerDatabase, Project project, Supplier supplier, Manufacturer manufacturer,
                  String comment, String document, String analysis, OrderStatus orderStatus, ThreeQuencher threeQuencher,
                  FiveDye fiveDye, Date date, User user) {


        this.name = name;
        this.sequence = sequence;
        this.orientation = orientation;
        this.freezer = freezer;
        this.drawer = drawer;
        this.box = box;
        this.positionInReference = positionInReference;
        this.Tm = Tm == null ? 0 : Tm;
        this.optimalTOfAnnealing = optimalTOfAnnealing == null ? 0 : optimalTOfAnnealing;
        this.purificationMethod = purificationMethod;
        this.amountAvailable = amountAvailable == null ? 0 : amountAvailable;
        this.amountAvailablePacks = amountAvailablePacks == null ? 0 : amountAvailablePacks;
        this.amountAvailablePackType = amountAvailablePackType;
        this.lengthOfAmplicone = lengthOfAmplicone == null ? 0 : lengthOfAmplicone;
        this.storingT = storingT == null || storingT.equals("") ? "No value" : storingT;
        this.GCPercent = GCPercent == null ? 0 : GCPercent;
        this.organism = organism;
        this.gen = gen;
        this.ncbiGenId = ncbiGenId;
        this.humanGenomBuild = humanGenomBuild;
        this.formulation = formulation;
        this.typeOfPrimer = typeOfPrimer;
        this.sondaSequence = sondaSequence == null || sondaSequence.equals("") ? "No value" : sondaSequence;
        this.assayId = assayId == null || assayId.equals("") ? "No value" : assayId;
        this.size = size;
        this.primerApplication = primerApplication;
        this.applicationComment = applicationComment == null || applicationComment.equals("") ? "No value" : applicationComment;
        this.fiveModification = fiveModification;
        this.threeModification = threeModification;
        this.concentrationOrdered = concentrationOrdered == null ? 0 : concentrationOrdered;
        this.concentrationOrderedUnit = concentrationOrderedUnit;
        this.checkSpecifityInBlast = checkSpecifityInBlast == null ? false : checkSpecifityInBlast;
        this.designerName = designerName;
        this.designerPublication = designerPublication;
        this.designerDatabase = designerDatabase;
        this.project = project;
        this.supplier = supplier;
        this.manufacturer = manufacturer;
        this.comment = comment == null || comment.equals("") ? "No value" : comment;
        this.document = document == null || document.equals("") ? "No value" : document;
        this.threeQuencher = threeQuencher;
        this.fiveDye = fiveDye;
        this.date = date;
        this.user = user;
        this.orderStatus = orderStatus;
        this.analysis = analysis == null || analysis.equals("") ? "No value" : analysis;
        this.length = sequence.length();
        this.deleted = false;
    }

    @PreRemove
    public void deletePrimer() {
        this.deleted = true;
    }

    public void generateName() {

        String delimiter = "-";
        String generatedName = "";

        try {
            if (orientation == Orientation.REVERSE) {
                generatedName += "R";
            } else if (orientation == Orientation.FORWARD) {
                generatedName += "F";
            } else {
                generatedName += "X";
            }

            generatedName += delimiter;

            String organismName = organism.getOrganism();
            switch (organismName) {
                case "Escherichia coli TG1":
                    generatedName += "G1";
                    break;
                case "Escherichia coli WK6":
                    generatedName += "K6";
                    break;
                case "Homo sapiens":
                    generatedName += "HS";
                    break;
                case "Mus musculus":
                    generatedName += "MM";
                    break;
                case "Rattus norvegicus domestica":
                    generatedName += "RN";
                    break;
                default:
                    generatedName += "XX";
                    break;
            }
            generatedName += delimiter;

            if (ncbiGenId == null) {
                generatedName += "XXX";
            } else if (ncbiGenId.getNcbiGenId().isEmpty()) {
                generatedName += "XXX";
            } else {
                generatedName += ncbiGenId.getNcbiGenId();
            }
            generatedName += delimiter;

            generatedName += String.valueOf(id);
        } catch (Exception ignored) {

        }
        this.generatedName = generatedName;
    }

    public void generateLength() {
        this.length = sequence.length();
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
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
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
    public Double getTm() {
        return Tm;
    }

    public void setTm(Double tm) {
        Tm = tm;
    }

    @JsonProperty
    public Double getOptimalTOfAnnealing() {
        return optimalTOfAnnealing;
    }

    public void setOptimalTOfAnnealing(Double optimalTOfAnnealing) {
        this.optimalTOfAnnealing = optimalTOfAnnealing;
    }

    public PurificationMethod getPurificationMethod() {
        return purificationMethod;
    }

    public void setPurificationMethod(PurificationMethod purificationMethod) {
        this.purificationMethod = purificationMethod;
    }

    @JsonProperty
    public Double getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Double amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    @JsonProperty
    public Integer getAmountAvailablePacks() {
        return amountAvailablePacks;
    }

    public void setAmountAvailablePacks(Integer amountAvailablePacks) {
        this.amountAvailablePacks = amountAvailablePacks;
    }

    @JsonProperty
    public AmountAvailablePackType getAmountAvailablePackType() {
        return amountAvailablePackType;
    }

    public void setAmountAvailablePackType(AmountAvailablePackType amountAvailablePackType) {
        this.amountAvailablePackType = amountAvailablePackType;
    }

    @JsonProperty
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty
    public Integer getLengthOfAmplicone() {
        return lengthOfAmplicone;
    }

    public void setLengthOfAmplicone(Integer lengthOfAmplicone) {
        this.lengthOfAmplicone = lengthOfAmplicone;
    }

    @JsonProperty
    public String getStoringT() {
        return storingT;
    }

    public void setStoringT(String storingT) {
        this.storingT = storingT;
    }

    @JsonProperty
    public Double getGCPercent() {
        return GCPercent;
    }

    public void setGCPercent(Double GCPercent) {
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
    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    @JsonProperty
    public NcbiGenId getNcbiGenId() {
        return ncbiGenId;
    }

    public void setNcbiGenId(NcbiGenId ncbiGenId) {
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
    public Double getConcentrationOrdered() {
        return concentrationOrdered;
    }

    public void setConcentrationOrdered(Double concentrationOrdered) {
        this.concentrationOrdered = concentrationOrdered;
    }

    @JsonProperty
    public ConcentrationOrderedUnit getConcentrationOrderedUnit() {
        return concentrationOrderedUnit;
    }

    public void setConcentrationOrderedUnit(ConcentrationOrderedUnit concentrationOrderedUnit) {
        this.concentrationOrderedUnit = concentrationOrderedUnit;
    }

    public Boolean isCheckSpecifityInBlast() {
        return checkSpecifityInBlast;
    }

    public void setCheckSpecifityInBlast(Boolean checkSpecifityInBlast) {
        this.checkSpecifityInBlast = checkSpecifityInBlast;
    }

    @JsonProperty
    public DesignerName getDesignerName() {
        return designerName;
    }

    public void setDesignerName(DesignerName designerName) {
        this.designerName = designerName;
    }

    @JsonProperty
    public DesignerPublication getDesignerPublication() {
        return designerPublication;
    }

    public void setDesignerPublication(DesignerPublication designerPublication) {
        this.designerPublication = designerPublication;
    }

    @JsonProperty
    public DesignerDatabase getDesignerDatabase() {
        return designerDatabase;
    }

    public void setDesignerDatabase(DesignerDatabase designerDatabase) {
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
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty
    public ThreeQuencher getThreeQuencher() {
        return threeQuencher;
    }

    public void setThreeQuencher(ThreeQuencher threeQuencher) {
        this.threeQuencher = threeQuencher;
    }

    @JsonProperty
    public FiveDye getFiveDye() {
        return fiveDye;
    }

    public void setFiveDye(FiveDye fiveDye) {
        this.fiveDye = fiveDye;
    }

    @JsonProperty
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty
    public Set<Long> getLinked() {
        return linked;
    }

    public void setLinked(Set<Long> linked) {
        this.linked = linked;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

