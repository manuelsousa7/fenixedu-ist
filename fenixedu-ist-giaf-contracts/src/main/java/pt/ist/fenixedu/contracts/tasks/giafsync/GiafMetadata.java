/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST GIAF Contracts.
 *
 * FenixEdu IST GIAF Contracts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST GIAF Contracts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST GIAF Contracts.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.contracts.tasks.giafsync;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.fenixedu.academic.domain.Country;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.person.IDDocumentType;
import org.fenixedu.academic.domain.person.MaritalStatus;
import org.fenixedu.commons.i18n.LocalizedString;
import org.fenixedu.bennu.core.domain.Bennu;
import org.fenixedu.commons.StringNormalizer;
import org.slf4j.Logger;

import pt.ist.fenixedu.contracts.domain.Employee;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.Absence;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.ContractSituation;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.FunctionsAccumulation;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.GrantOwnerEquivalent;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.ProfessionalCategory;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.ProfessionalContractType;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.ProfessionalRegime;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.ProfessionalRelation;
import pt.ist.fenixedu.contracts.domain.personnelSection.contracts.ServiceExemption;
import pt.ist.fenixedu.contracts.domain.util.CategoryType;

class GiafMetadata {
    private Map<Integer, Employee> employees = null;

    private final Map<String, Country> countries = new HashMap<String, Country>();

    private final Map<String, MaritalStatus> maritalStatus = new HashMap<String, MaritalStatus>();

    private final Map<String, IDDocumentType> documentTypes = new HashMap<String, IDDocumentType>();

    private final Map<String, ContractSituation> contractSituations = new HashMap<String, ContractSituation>();

    private final Map<String, ProfessionalCategory> professionalCategories = new HashMap<String, ProfessionalCategory>();

    private final Map<String, ProfessionalRelation> professionalRelations = new HashMap<String, ProfessionalRelation>();

    private final Map<String, ProfessionalContractType> professionalContractTypes =
            new HashMap<String, ProfessionalContractType>();

    private final Map<String, ProfessionalRegime> professionalRegimes = new HashMap<String, ProfessionalRegime>();

    private final Map<String, FunctionsAccumulation> functionsAccumulations = new HashMap<String, FunctionsAccumulation>();

    private final Map<String, GrantOwnerEquivalent> grantOwnerEquivalences = new HashMap<String, GrantOwnerEquivalent>();

    private final Map<String, ServiceExemption> serviceExemptions = new HashMap<String, ServiceExemption>();

    private final Map<String, Absence> absences = new HashMap<String, Absence>();

    public GiafMetadata() {
        for (FunctionsAccumulation functionsAccumulation : Bennu.getInstance().getFunctionsAccumulationsSet()) {
            functionsAccumulations.put(functionsAccumulation.getGiafId(), functionsAccumulation);
        }
        for (GrantOwnerEquivalent grantOwnerEquivalent : Bennu.getInstance().getGrantOwnerEquivalencesSet()) {
            grantOwnerEquivalences.put(grantOwnerEquivalent.getGiafId(), grantOwnerEquivalent);
        }
        for (ServiceExemption serviceExemption : Bennu.getInstance().getServiceExemptionsSet()) {
            serviceExemptions.put(serviceExemption.getGiafId(), serviceExemption);
        }
        for (ProfessionalContractType professionalContractType : Bennu.getInstance().getProfessionalContractTypesSet()) {
            professionalContractTypes.put(professionalContractType.getGiafId(), professionalContractType);
        }
        for (Absence absence : Bennu.getInstance().getAbsencesSet()) {
            absences.put(absence.getGiafId(), absence);
        }
        for (ContractSituation contractSituation : Bennu.getInstance().getContractSituationsSet()) {
            contractSituations.put(contractSituation.getGiafId(), contractSituation);
        }
        for (ProfessionalCategory professionalCategory : Bennu.getInstance().getProfessionalCategoriesSet()) {
            professionalCategories.put(professionalCategory.getGiafId(), professionalCategory);
        }
        for (ProfessionalRegime professionalRegime : Bennu.getInstance().getProfessionalRegimesSet()) {
            professionalRegimes.put(professionalRegime.getGiafId(), professionalRegime);
        }
        for (ProfessionalRelation professionalRelation : Bennu.getInstance().getProfessionalRelationsSet()) {
            professionalRelations.put(professionalRelation.getGiafId(), professionalRelation);
        }
        for (Country country : Country.readDistinctCountries()) {
            countries.put(StringNormalizer.normalize(country.getName()), country);
        }
        countries.put("checa (republica)", Country.readByThreeLetterCode("CZE"));
        countries.put("irao (republica islamica do)", Country.readByThreeLetterCode("IRN"));
        countries.put("coreia (republica da)", Country.readByThreeLetterCode("KOR"));
        countries.put("coreia (rep.pop.dem.da)", Country.readByThreeLetterCode("PRK"));
        countries.put("emiratos arabes unidos", Country.readByThreeLetterCode("ARE"));
        countries.put("zimbabwe", Country.readByThreeLetterCode("ZWE"));
        countries.put("servia-e-montenegro", Country.readByThreeLetterCode("SCG"));
        countries.put("libia (jamahiriya arabe da)", Country.readByThreeLetterCode("LBY"));
        countries.put("mauricias", Country.readByThreeLetterCode("MUS"));
        countries.put("bosnia-e-herzegovina", Country.readByThreeLetterCode("BIH"));
        countries.put("paises baixos", Country.readByThreeLetterCode("NLD"));

        maritalStatus.put("ST", MaritalStatus.SINGLE);
        maritalStatus.put("CS", MaritalStatus.MARRIED);
        maritalStatus.put("VV", MaritalStatus.WIDOWER);
        maritalStatus.put("DV", MaritalStatus.DIVORCED);
        maritalStatus.put("SJ", MaritalStatus.SEPARATED);
        maritalStatus.put("UF", MaritalStatus.CIVIL_UNION);
        maritalStatus.put("D", MaritalStatus.UNKNOWN);

        documentTypes.put("AUT", IDDocumentType.RESIDENCE_AUTHORIZATION);
        documentTypes.put("BI", IDDocumentType.IDENTITY_CARD);
        // CP N.Cedula Pessoal
        documentTypes.put("P", IDDocumentType.PASSPORT);
        // BN Boletim Nascimento
        documentTypes.put("BI-E", IDDocumentType.FOREIGNER_IDENTITY_CARD);
        documentTypes.put("BI-M", IDDocumentType.NAVY_IDENTITY_CARD);
        documentTypes.put("BI-P", IDDocumentType.NATIVE_COUNTRY_IDENTITY_CARD);
        documentTypes.put("BI-F", IDDocumentType.AIR_FORCE_IDENTITY_CARD);
        documentTypes.put("CC", IDDocumentType.IDENTITY_CARD);
    }

    public Absence absence(String giafId) {
        return absences.get(giafId);
    }

    public void registerAbsence(String giafId, final LocalizedString name) {
        absences.put(giafId, new Absence(giafId, name));
    }

    public Collection<Absence> absences() {
        return absences.values();
    }

    public FunctionsAccumulation accumulation(String giafId) {
        return functionsAccumulations.get(giafId);
    }

    public void registerAccumulation(String giafId, final LocalizedString name) {
        functionsAccumulations.put(giafId, new FunctionsAccumulation(giafId, name));
    }

    public ProfessionalCategory category(String giafId) {
        return professionalCategories.get(giafId);
    }

    public void registerCategory(String giafId, CategoryType categoryType, LocalizedString description) {
        professionalCategories.put(giafId, new ProfessionalCategory(giafId, description, categoryType));
    }

    public ProfessionalContractType contractType(String giafId) {
        return professionalContractTypes.get(giafId);
    }

    public void registerContractType(String giafId, final LocalizedString name) {
        professionalContractTypes.put(giafId, new ProfessionalContractType(giafId, name));
    }

    public Country country(String name) {
        return countries.get(name);
    }

    public ServiceExemption exemption(String giafId) {
        return serviceExemptions.get(giafId);
    }

    public void registerExemption(String giafId, final LocalizedString name) {
        serviceExemptions.put(giafId, new ServiceExemption(giafId, name));
    }

    public Employee getEmployee(String employeeNumberString, Logger logger) {
        if (employees == null) {
            employees = new HashMap<Integer, Employee>();
            for (Employee employee : Bennu.getInstance().getEmployeesSet()) {
                employees.put(employee.getEmployeeNumber(), employee);
            }
        }
        try {
            return employees.get(Integer.parseInt(employeeNumberString));
        } catch (NumberFormatException e) {
            logger.debug("Number problem: " + employeeNumberString);
        }
        return null;
    }

    protected Map<Integer, Employee> getEmployeesMap() {
        Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
        for (Employee employee : Bennu.getInstance().getEmployeesSet()) {
            employees.put(employee.getEmployeeNumber(), employee);
        }
        return employees;
    }

    public Person getPerson(String employeeNumberString, Logger logger) {
        Employee employee = getEmployee(employeeNumberString, logger);
        return employee != null ? employee.getPerson() : null;
    }

    public GrantOwnerEquivalent grantOwnerEquivalent(String giafId) {
        return grantOwnerEquivalences.get(giafId);
    }

    public void registerGrantOwnerEquivalent(String giafId, final LocalizedString name) {
        grantOwnerEquivalences.put(giafId, new GrantOwnerEquivalent(giafId, name));
    }

    public ProfessionalRegime regime(String giafId) {
        return professionalRegimes.get(giafId);
    }

    public void registerRegime(String giafId, Integer weighting, BigDecimal fullTimeEquivalent, CategoryType categoryType,
            LocalizedString name) {
        professionalRegimes.put(giafId, new ProfessionalRegime(giafId, name, weighting, fullTimeEquivalent, categoryType));
    }

    public ProfessionalRelation relation(String giafId) {
        return professionalRelations.get(giafId);
    }

    public void registerRelation(String giafId, Boolean fullTimeEquivalent, LocalizedString name) {
        professionalRelations.put(giafId, new ProfessionalRelation(giafId, name, fullTimeEquivalent));
    }

    public ContractSituation situation(String giafId) {
        return contractSituations.get(giafId);
    }

    public void registerSituation(String giafId, Boolean endSituation, Boolean serviceExemption, LocalizedString description) {
        contractSituations.put(giafId, new ContractSituation(giafId, description, endSituation, serviceExemption));
    }

    public MaritalStatus maritalStatus(String key) {
        return maritalStatus.get(key);
    }

    public IDDocumentType documentType(String idDocTypeString) {
        return documentTypes.get(idDocTypeString);
    }
}
