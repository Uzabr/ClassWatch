import { useLocation } from "react-router-dom";
import { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";

const ReportPage = () => {
    const location = useLocation();
    const result = location.state;
    const [selectedKey, setSelectedKey] = useState<string | null>(null);

    const metrics = [
        { key: "total", label: "Всего студентов", value: result.total },
        { key: "frozen", label: "Заморожены", value: result.frozen },
        { key: "blocked", label: "Заблокированы", value: result.blocked },
        { key: "overdue", label: "Просроченные", value: result.overdue },
        { key: "belowTarget", label: "Не достигли цели", value: result.belowTarget },
        { key: "soonDeadline", label: "Меньше 10 дней", value: result.soonDeadlineCount },
    ];

    const studentMap: Record<string, any[]> = {
        total: result.totalStudents,
        frozen: result.frozenStudents,
        blocked: result.blockedStudents,
        overdue: result.overdueStudents,
        belowTarget: result.belowTargetStudents,
        soonDeadline: result.soonDeadlineStudents,
    };

    const toggleSelection = (key: string) => {
        setSelectedKey((prev) => (prev === key ? null : key));
    };

    return (
        <div className="min-h-screen bg-gray-50 flex flex-col p-10">
            <h1 className="text-3xl font-bold text-center mb-8">📊 Отчёт по студентам</h1>

            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 mb-10">
                {metrics.map((m, i) => (
                    <motion.div
                        key={m.key}
                        onClick={() => toggleSelection(m.key)}
                        whileHover={{ scale: 1.05 }}
                        className="cursor-pointer bg-white rounded-xl p-5 shadow-md text-center border border-gray-200 hover:shadow-lg transition"
                    >
                        <h3 className="text-sm text-gray-500 mb-2">{m.label}</h3>
                        <motion.p
                            className="text-2xl font-bold text-blue-700"
                            initial={{ scale: 0 }}
                            animate={{ scale: 1 }}
                            transition={{ delay: 0.1 * i }}
                        >
                            {m.value}
                        </motion.p>
                    </motion.div>
                ))}
            </div>

            <AnimatePresence>
                {selectedKey && Array.isArray(studentMap[selectedKey]) && (
                    <motion.div
                        initial={{ opacity: 0, x: -40 }}
                        animate={{ opacity: 1, x: 0 }}
                        exit={{ opacity: 0, x: -40 }}
                        className="bg-white p-6 rounded-xl shadow-lg max-w-md border border-blue-200"
                    >
                        <h2 className="text-xl font-semibold mb-4 text-blue-600">
                            📋 {metrics.find((m) => m.key === selectedKey)?.label}
                        </h2>
                        <ul className="space-y-2 max-h-[400px] overflow-y-auto pr-2">
                            {studentMap[selectedKey].map((student, i) => (
                                <li
                                    key={i}
                                    className="text-gray-800 text-sm hover:scale-105 transition-transform cursor-pointer"
                                >
                                    {student.login}
                                </li>
                            ))}
                        </ul>
                    </motion.div>
                )}
            </AnimatePresence>
        </div>
    );
};

export default ReportPage;

