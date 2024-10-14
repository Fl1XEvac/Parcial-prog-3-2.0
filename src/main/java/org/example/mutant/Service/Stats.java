package org.example.mutant.Service;


public class Stats {

    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

    public Stats(long countMutantDna, long countHumanDna, double ratio) {
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    public long getCountMutantDna() {
        return countMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }
}

