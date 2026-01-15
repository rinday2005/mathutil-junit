/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ntpnhi.lab01.core;

/**
 *
 * @author ADMIN
 */

public class InsuranceClaim {

    private String claimId;
    private double amount;
    private String claimStatus;

    private static final double PAYOUT_RATE = 0.85;

    /**
     * Constructor to initialize the insurance claim.
     *
     * @param id Claim ID
     * @param claimAmount Amount claimed
     * @throws IllegalArgumentException if amount is zero or negative
     */
    public InsuranceClaim(String id, double claimAmount) {
        if (claimAmount <= 0) {
            throw new IllegalArgumentException("Claim amount must be positive.");
        }
        this.claimId = id;
        this.amount = claimAmount;
        this.claimStatus = "Pending";
    }

    /**
     * Processes the claim by updating its status if currently pending.
     *
     * @param statusUpdate New status to apply
     * @return true if update was applied, false otherwise
     */
    public boolean processClaim(String statusUpdate) {
        if ("Pending".equals(this.claimStatus)) {
            this.claimStatus = statusUpdate;
            return true;
        }
        return false;
    }

    /**
     * Calculates payout based on approval.
     *
     * @return payout amount or 0 if not approved
     */
    public double calculatePayout() {
        if ("Approved".equals(this.claimStatus)) {
            return amount * PAYOUT_RATE;
        }
        return 0;
    }

    /**
     * Updates the amount of the claim.
     *
     * @param newAmount new claim amount
     */
    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("New amount must be positive.");
        }
        this.amount = newAmount;
    }

    // Getters
    public String getClaimId() {
        return claimId;
    }

    public double getAmount() {
        return amount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{"
                + "claimId='" + claimId + '\''
                + ", amount=" + amount
                + ", claimStatus='" + claimStatus + '\''
                + '}';
    }
}

