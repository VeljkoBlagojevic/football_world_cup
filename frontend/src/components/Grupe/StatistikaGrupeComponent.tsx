import React, { FC, useEffect } from "react";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import { StatistikaReprezentacije } from "../../domain/StatistikaReprezentacije";

interface StatistikaGrupeProps {
  statistike: StatistikaReprezentacije[] | undefined;
}

const StatistikaGrupeComponent: FC<StatistikaGrupeProps> = ({ statistike }) => {
  useEffect(() => {
    if (statistike) {
      // Call a function to generate Highcharts options using statistike data
      const options = generateChartOptions(statistike);
      setChartOptions(options as Highcharts.Options);
    }
  }, [statistike]);

  const [chartOptions, setChartOptions] = React.useState<Highcharts.Options>({
    /* Initial or default chart options */
  });

  const generateChartOptions = (
    data: StatistikaReprezentacije[]
  ): Highcharts.Options => {
    // Extract relevant data and create Highcharts options
    const categories = data.map((item) => item.reprezentacija?.naziv ?? "");
    const seriesData = data.map((item) => item.brojOsvojenihPoena ?? 0);

    return {
      chart: {
        type: "bar",
        backgroundColor: "rgba(86, 4, 44, 0.75)",
      },
      title: {
        text: "Statistike po reprezentacijama",
        style: {
          color: "white",
        },
      },
      xAxis: {
        categories,
        title: {
          text: "Reprezentacije",
          style: {
            color: "white",
          },
        },
        labels: {
          style: {
            color: "white",
          },
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: "Bodovi",
          style: {
            color: "white",
          },
        },
        labels: {
          style: {
            color: "white",
          },
        },
      },
      legend: {
        enabled: false,
      },
      series: [
        {
          name: "Points",
          data: seriesData,
          color: "rgba(255, 201, 71, 0.75)",
          type: "bar",
        },
      ],
    };
  };

  return (
    <div className="grupe">
      <HighchartsReact highcharts={Highcharts} options={chartOptions} />
    </div>
  );
};

export default StatistikaGrupeComponent;
