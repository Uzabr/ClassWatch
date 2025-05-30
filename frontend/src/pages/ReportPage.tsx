import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import { motion, AnimatePresence } from "framer-motion";
import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

interface Student {
  login: string;
  status: string;
  tribe: string;
  level: number;
  targetLevel: number;
  deadline: number;
  daysToDeadline: number;
}

interface ReportData {
  total: number;
  frozen: number;
  blocked: number;
  overdue: number;
  blowTarget: number;
  soonDeadlineCount: number;
  totalStudents: Student[];
  frozenStudents: Student[];
  blockedStudents: Student[];
  overdueStudents: Student[];
  blowTargetStudents: Student[];
  soonDeadline: Student[];
}

const COLORS = ["#60A5FA", "#F87171", "#FBBF24", "#34D399", "#A78BFA"];

const ReportPage: React.FC = () => {
  const location = useLocation();
  const result = location.state as ReportData;
  const [selectedKey, setSelectedKey] = useState<string | null>(null);

  const toggleSelection = (key: string) => {
    setSelectedKey(prev => (prev === key ? null : key));
  };

  const metrics = [
    { key: "total", label: "Всего студентов", value: result.total },
    { key: "frozen", label: "Заморожены", value: result.frozen },
    { key: "blocked", label: "Заблокированы", value: result.blocked },
    { key: "overdue", label: "С просрочкой", value: result.overdue },
    { key: "belowTarget", label: "Не достигли цели", value: result.blowTarget },
    { key: "soonDeadline", label: "Скоро дедлайн", value: result.soonDeadlineCount },
  ];

  const studentMap: Record<string, Student[]> = {
    total: result.totalStudents,
    frozen: result.frozenStudents,
    blocked: result.blockedStudents,
    overdue: result.overdueStudents,
    belowTarget: result.blowTargetStudents,
    soonDeadline: result.soonDeadline,
  };

  const chartData = metrics.slice(1).map((m, i) => ({
    name: m.label,
    value: m.value,
  }));

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col p-10">
      <h1 className="text-3xl font-bold text-center mb-8">📊 Отчёт по студентам</h1>

      {/* Карточки */}
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

      {/* График */}
      <div className="bg-white p-6 rounded-xl shadow-lg border border-gray-200 mb-10">
        <h2 className="text-xl font-semibold mb-4 text-center text-gray-700">📈 Распределение по статусам</h2>
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie
              data={chartData}
              cx="50%"
              cy="50%"
              labelLine={false}
              label={({ name }) => name}
              outerRadius={100}
              fill="#8884d8"
              dataKey="value"
            >
              {chartData.map((entry, index) => (
                <Cell key={index} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip />
            <Legend />
          </PieChart>
        </ResponsiveContainer>
      </div>

      {/* Список студентов */}
      <AnimatePresence>
        {selectedKey && (
          <motion.div
            key={selectedKey}
            initial={{ x: "-120%", opacity: 0 }}
            animate={{ x: 0, opacity: 1 }}
            exit={{ x: "-120%", opacity: 0 }}
            transition={{
              type: "spring",
              stiffness: 280,
              damping: 24,
              duration: 0.4,
            }}
            className="fixed left-6 top-24 bg-white z-40 w-full max-w-md p-6 rounded-xl shadow-2xl border border-blue-200"
          >
            <h2 className="text-xl font-semibold mb-4 text-blue-600">
              📋 {metrics.find((m) => m.key === selectedKey)?.label}
            </h2>
            <ul className="space-y-2 max-h-[400px] overflow-y-auto pr-2">
              <AnimatePresence>
                {Array.isArray(studentMap[selectedKey]) &&
                  studentMap[selectedKey].map((student, i) => (
                    <motion.li
                      key={i}
                      initial={{ opacity: 0, y: 10 }}
                      animate={{ opacity: 1, y: 0 }}
                      exit={{ opacity: 0, y: 10 }}
                      transition={{ delay: i * 0.03 }}
                      whileHover={{ scale: 1.07 }}
                      className="text-gray-800 text-sm font-medium bg-gray-50 px-3 py-2 rounded-lg shadow-sm hover:bg-blue-50 transition"
                    >
                      {student.login}
                    </motion.li>
                  ))}
              </AnimatePresence>
            </ul>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
};

export default ReportPage;
