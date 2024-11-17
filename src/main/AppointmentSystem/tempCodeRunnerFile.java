    public void viewAllAvailableAppointments() {
        loader.loadInitialAppointments();
        System.out.print("Please enter date in yyyy-mm-dd format");
        String dateInput = sc.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
            return;
        }
        patientApptViewer.viewpatientViewScheduleForNextThreeDays(date);
        saver.saveRecords();
    }