package controller;

import data.Data;
import data.Deserialise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentListTest {

    @BeforeEach
    void setUp() {
        Deserialise.deserialise();
    }

    @AfterEach
    void tearDown() {
        Data.data = null;
    }

    @Test
    void populate() {

    }
}