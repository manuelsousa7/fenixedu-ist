/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST QUC.
 *
 * FenixEdu IST QUC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST QUC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST QUC.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.quc.domain;

import java.util.Collection;

import org.fenixedu.academic.domain.ExecutionSemester;
import org.fenixedu.bennu.core.domain.Bennu;
import org.joda.time.DateTime;

public class CurricularCourseInquiryTemplate extends CurricularCourseInquiryTemplate_Base {

    public CurricularCourseInquiryTemplate(DateTime begin, DateTime end) {
        super();
        init(begin, end);
    }

    public static CurricularCourseInquiryTemplate getCurrentTemplate() {
        final Collection<InquiryTemplate> inquiryTemplates = Bennu.getInstance().getInquiryTemplatesSet();
        for (final InquiryTemplate inquiryTemplate : inquiryTemplates) {
            if (inquiryTemplate instanceof CurricularCourseInquiryTemplate && inquiryTemplate.isOpen()) {
                return (CurricularCourseInquiryTemplate) inquiryTemplate;
            }
        }
        return null;
    }

    public static CurricularCourseInquiryTemplate getTemplateByExecutionPeriod(ExecutionSemester executionSemester) {
        final Collection<InquiryTemplate> inquiryTemplates = Bennu.getInstance().getInquiryTemplatesSet();
        for (final InquiryTemplate inquiryTemplate : inquiryTemplates) {
            if (inquiryTemplate instanceof CurricularCourseInquiryTemplate
                    && executionSemester == inquiryTemplate.getExecutionPeriod()) {
                return (CurricularCourseInquiryTemplate) inquiryTemplate;
            }
        }
        return null;
    }

}
