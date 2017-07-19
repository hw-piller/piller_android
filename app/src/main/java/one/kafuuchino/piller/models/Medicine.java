package one.kafuuchino.piller.models;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public class Medicine {
    private String number, name, saveMedicine, division, ingridient, use;

    public Medicine(String number, String name, String saveMedicine, String division, String ingridient, String use) {
        this.number = number;
        this.name = name;
        this.saveMedicine = saveMedicine;
        this.division = division;
        this.ingridient = ingridient;
        this.use = use;
    }

    public Medicine() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaveMedicine() {
        return saveMedicine;
    }

    public void setSaveMedicine(String saveMedicine) {
        this.saveMedicine = saveMedicine;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getIngridient() {
        return ingridient;
    }

    public void setIngridient(String ingridient) {
        this.ingridient = ingridient;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
}
