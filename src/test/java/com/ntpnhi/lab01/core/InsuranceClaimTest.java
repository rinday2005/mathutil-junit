package com.ntpnhi.lab01.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class InsuranceClaimTest {

    private InsuranceClaim claim;

    @BeforeAll
    static void beforeAll() {
        System.out.println("=== START ALL TESTS ===");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=== END ALL TESTS ===");
    }

    @BeforeEach
    void setUp() {
        System.out.println("-> Setup before test");
        claim = new InsuranceClaim("C001", 1000.0);
    }

    @AfterEach
    void tearDown() {
        System.out.println("<- Cleanup after test");
    }

    @Test
    @DisplayName("Constructor initializes correctly")
    void testConstructorInitializesValues() {
        assertEquals("C001", claim.getClaimId());
        assertEquals(1000.0, claim.getAmount());
        assertEquals("Pending", claim.getClaimStatus());
    }

    @Test
    @DisplayName("Constructor throws exception for invalid amount")
    void testConstructorInvalidAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new InsuranceClaim("C002", -500));
    }

    @Test
    @DisplayName("processClaim updates status if Pending")
    void testProcessClaimWhenPending() {
        boolean result = claim.processClaim("Approved");
        assertTrue(result);
        assertEquals("Approved", claim.getClaimStatus());
    }

    @Test
    @DisplayName("processClaim returns false if not Pending")
    void testProcessClaimWhenNotPending() {
        claim.processClaim("Approved");
        boolean result = claim.processClaim("Rejected");

        assertFalse(result);
        assertEquals("Approved", claim.getClaimStatus());
    }

    @Test
    @DisplayName("calculatePayout returns correct amount when Approved")
    void testCalculatePayoutApproved() {
        claim.processClaim("Approved");
        assertEquals(850.0, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("calculatePayout returns 0 if not Approved")
    void testCalculatePayoutNotApproved() {
        assertEquals(0, claim.calculatePayout());
    }

    @Test
    @DisplayName("updateClaimAmount updates successfully")
    void testUpdateClaimAmount() {
        claim.updateClaimAmount(2000.0);
        assertEquals(2000.0, claim.getAmount());
    }

    @Test
    @DisplayName("updateClaimAmount throws exception for invalid amount")
    void testUpdateClaimAmountInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> claim.updateClaimAmount(0));
    }

    @ParameterizedTest
    @CsvSource({
        "Approved,850.0",
        "Rejected,0.0",
        "Pending,0.0"
    })
    @DisplayName("calculatePayout returns correct value for different statuses")
    void testCalculatePayoutVariousStatuses(String status, double expectedPayout) {

        InsuranceClaim claim = new InsuranceClaim("C001", 1000.0);

        if ("Approved".equals(status)) {
            claim.processClaim("Approved");
        }

        assertEquals(expectedPayout, claim.calculatePayout(), 0.001);
    }

    @Test
    @DisplayName("toString returns expected format")
    void testToStringFormat() {
        String output = claim.toString();

        assertTrue(output.contains("InsuranceClaim"));
        assertTrue(output.contains("claimId='C001'"));
        assertTrue(output.contains("amount=1000.0"));
        assertTrue(output.contains("claimStatus='Pending'"));
    }

    //Test constructor với claimId = null
    @Test
    @DisplayName("Constructor throws exception for null claim ID")
    void testConstructorNullClaimId() {
        assertThrows(IllegalArgumentException.class,
                () -> new InsuranceClaim(null, 1000.0));
    }

//Test processClaim(null)
    @Test
    @DisplayName("processClaim throws exception for null status")
    void testProcessClaimNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> claim.processClaim(null));
    }

}
